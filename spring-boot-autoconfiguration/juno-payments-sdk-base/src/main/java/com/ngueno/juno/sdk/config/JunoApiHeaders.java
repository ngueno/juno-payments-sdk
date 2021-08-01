package com.ngueno.juno.sdk.config;

public interface JunoApiHeaders {

    String X_API_VERSION = "X-Api-Version";
    String X_RESOURCE_TOKEN = "X-Resource-Token";
    String X_SIGNATURE = "X-Signature";
    String AUTHORIZATION = "Authorization";
    String CONTENT_TYPE = "Content-Type";
    String CONTENT_ENCODING = "Content-Encoding";
    String CONTENT_ENCODING_GZIP = "gzip";
    String GRANT_TYPE = "grant_type";
    String CLIENT_CREDENTIALS = "client_credentials";

    String BASIC_AUTHENTICATION = "Basic %s";
    String BEARER_AUTHENTICATION = "Bearer %s";
    String API_VERSION = "2";
}
