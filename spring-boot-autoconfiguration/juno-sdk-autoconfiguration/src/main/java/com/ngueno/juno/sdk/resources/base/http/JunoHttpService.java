package com.ngueno.juno.sdk.resources.base.http;

import static com.ngueno.juno.sdk.config.JunoApiHeaders.API_VERSION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.AUTHORIZATION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.BEARER_AUTHENTICATION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.CONTENT_TYPE;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.X_API_VERSION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.X_RESOURCE_TOKEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.ngueno.juno.sdk.config.JunoApiHeaders;
import com.ngueno.juno.sdk.model.environment.JunoEnvironment;
import com.ngueno.juno.sdk.model.error.JunoApiError;
import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.resources.oauth.ProxyJunoOAuthService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class JunoHttpService {

    @PostConstruct
    public void configure() {
        webClient = WebClient //
                .builder() //
                .baseUrl(junoEnvironment.getApiv2Endpoint()) //
                .defaultHeader(X_API_VERSION, API_VERSION) //
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE) //
                .build();
    }

    public <T> T get(String uri, Class<T> responseClass) {
        return get(uri, null, responseClass);
    }

    public <T> T get(String uri, String resourceToken, Class<T> responseClass) {
        return get(uri, resourceToken, responseClass, Map.of());
    }

    public <T> T get(String uri, String resourceToken, Class<T> responseClass, Map<String, Object> params) {
        return webClient //
                .get() //
                .uri(uriBuilder -> {
                    uriBuilder.path(uri);
                    params.entrySet() //
                            .stream() //
                            .filter(entry -> Objects.nonNull(entry.getValue())) //
                            .forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue())); //
                    return uriBuilder.build();
                }) //
                .headers(configureHeaders(resourceToken)) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    public <T> T post(String uri, String resourceToken, Class<T> responseClass) {
        return post(uri, resourceToken, null, responseClass);
    }

    public <T> T post(String uri, String resourceToken, JunoBaseRequest request, Class<T> responseClass) {
        return webClient //
                .post() //
                .uri(uri) //
                .body(toBody(request)) //
                .headers(configureHeaders(resourceToken)) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    public <T> T put(String uri, String resourceToken, Class<T> responseClass) {
        return put(uri, resourceToken, null, responseClass);
    }

    public <T> T put(String uri, String resourceToken, JunoBaseRequest request, Class<T> responseClass) {
        return webClient //
                .put() //
                .uri(uri) //
                .body(toBody(request)) //
                .headers(configureHeaders(resourceToken)) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    public <T> T patch(String uri, String resourceToken, JunoBaseRequest request, Class<T> responseClass) {
        return webClient //
                .patch() //
                .uri(uri) //
                .body(BodyInserters.fromValue(request)) //
                .headers(configureHeaders(resourceToken)) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    public <T> T delete(String uri, String resourceToken, Class<T> responseClass) {
        return webClient //
                .delete() //
                .uri(uri) //
                .headers(configureHeaders(resourceToken)) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    public <T> T upload(String uri, String resourceToken, byte[] file, String fileName, Class<T> responseClass) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("files", file).filename(fileName);
        return upload(uri, resourceToken, builder, responseClass);
    }

    public <T> T upload(String uri, String resourceToken, MultipartBodyBuilder builder, Class<T> responseClass) {
        return webClient //
                .post() //
                .uri(uri) //
                .headers(configureHeaders(resourceToken)) //
                .body(BodyInserters.fromMultipartData(builder.build())) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    public <T> T uploadEncrypted(String uri, String resourceToken, byte[] encryptedFile, Class<T> responseClass) {
        return webClient //
                .post() //
                .uri(uri) //
                .headers(configureEncryptedUploadHeaders(resourceToken)) //
                .body(BodyInserters.fromResource(new ByteArrayResource(encryptedFile))) //
                .retrieve() //
                .onStatus(HttpStatus::isError, this::handleError) //
                .bodyToMono(responseClass) //
                .block(junoEnvironment.getApiv2TimeoutDuration());
    }

    private Consumer<HttpHeaders> configureHeaders(String resourceToken) {
        return headers -> {
            headers.add(AUTHORIZATION, String.format(BEARER_AUTHENTICATION, proxyJunoOAuthService.getAccessToken()));
            if (StringUtils.isNotBlank(resourceToken)) {
                headers.add(X_RESOURCE_TOKEN, resourceToken);
            }
        };
    }

    private Consumer<HttpHeaders> configureEncryptedUploadHeaders(String resourceToken) {
        return configureHeaders(resourceToken).andThen(headers -> {
            headers.add(JunoApiHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.add(JunoApiHeaders.CONTENT_ENCODING, JunoApiHeaders.CONTENT_ENCODING_GZIP);
        });
    }

    private Mono<? extends Throwable> handleError(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(JunoApiError.class).flatMap(apiError -> Mono.error(new JunoApiIntegrationException(apiError)));
    }

    private BodyInserter<?, ? super ClientHttpRequest> toBody(JunoBaseRequest request) {
        return request != null ? BodyInserters.fromValue(request) : null;
    }

    private WebClient webClient;

    @Resource
    private JunoEnvironment junoEnvironment;

    @Resource
    private ProxyJunoOAuthService proxyJunoOAuthService;
}
