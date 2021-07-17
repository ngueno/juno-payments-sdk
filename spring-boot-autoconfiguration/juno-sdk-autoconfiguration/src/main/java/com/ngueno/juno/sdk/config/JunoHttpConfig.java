package com.ngueno.juno.sdk.config;

import static com.ngueno.juno.sdk.config.JunoApiHeaders.API_VERSION;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.CONTENT_TYPE;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.X_API_VERSION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngueno.juno.sdk.model.environment.JunoEnvironment;
import com.ngueno.juno.sdk.model.error.JunoApiError;
import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class JunoHttpConfig {

    @Bean
    public WebClient webClient(JunoEnvironment junoEnvironment) {
        log.debug("[JUNO-SDK] Configuring WebClient");

        HttpClient httpClient = HttpClient.create().responseTimeout(junoEnvironment.getApiv2TimeoutDuration());

        return WebClient //
                .builder() //
                .clientConnector(new ReactorClientHttpConnector(httpClient)) //
                .baseUrl(junoEnvironment.getApiv2Endpoint()) //
                .defaultHeader(X_API_VERSION, API_VERSION) //
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE) //
                .build();
    }

    @Bean
    public ResponseErrorHandler customRestTemplateErrorHandler(ObjectMapper junoObjectMapper) {
        return new ResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
                return httpResponse.getStatusCode().isError();
            }

            @Override
            public void handleError(ClientHttpResponse httpResponse) throws IOException {
                throw new JunoApiIntegrationException(junoObjectMapper.readValue(httpResponse.getBody(), JunoApiError.class));
            }
        };
    }

    @Bean
    public RestTemplate restTemplate(JunoEnvironment junoEnvironment, ResponseErrorHandler customRestTemplateErrorHandler) {
        log.debug("[JUNO-SDK] Configuring RestTemplate");

        return new RestTemplateBuilder() //
                .errorHandler(customRestTemplateErrorHandler) //
                .rootUri(junoEnvironment.getApiv2Endpoint()) //
                .setConnectTimeout(junoEnvironment.getApiv2TimeoutDuration()) //
                .setReadTimeout(junoEnvironment.getApiv2TimeoutDuration()) //
                .defaultHeader(X_API_VERSION, API_VERSION) //
                .build(); //

    }

}
