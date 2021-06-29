package com.ngueno.juno.sdk.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import lombok.Getter;

public class HateoasDataParser {

    private static final String VALUE_SEPARATOR = "=";
    private static final String PARAMS_SEPARATOR = "&";

    private URL url;

    @Getter
    private Map<String, Object> params = new LinkedHashMap<>();

    public HateoasDataParser(String hateoasLink) {
        try {
            this.url = new URL(hateoasLink);
            setParams();
        } catch (MalformedURLException e) {
            throw new JunoApiIntegrationException("Failed to parse given link", e);
        }
    }

    private void setParams() {
        String query = url.getQuery();
        Stream.of(query.split(PARAMS_SEPARATOR)).forEach(entry -> {
            int idx = entry.indexOf(VALUE_SEPARATOR);
            params.put(entry.substring(0, idx), entry.substring(idx + 1));
        });
    }
}