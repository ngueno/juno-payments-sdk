package com.ngueno.juno.sdk.resources.validation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    @Getter
    private byte[] cachedRequestAsBytes;
    private String cachedRequestAsString;
    private JsonNode cachedRequestAsJson;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        cachedRequestAsBytes = StreamUtils.copyToByteArray(request.getInputStream());
    }

    public String getCachedRequestAsString() {
        if (StringUtils.isBlank(cachedRequestAsString)) {
            cachedRequestAsString = new String(cachedRequestAsBytes, StandardCharsets.UTF_8);
        }
        return cachedRequestAsString;
    }

    public JsonNode getCachedRequestAsJson() throws IOException {
        if (cachedRequestAsJson == null) {
            cachedRequestAsJson = new ObjectMapper().readTree(getCachedRequestAsBytes());
        }
        return cachedRequestAsJson;
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedBodyServletInputStream(cachedRequestAsBytes);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cachedRequestAsBytes)));
    }

    public static class CachedBodyServletInputStream extends ServletInputStream {

        private InputStream cachedBodyInputStream;

        public CachedBodyServletInputStream(byte[] cachedRequestAsBytes) {
            this.cachedBodyInputStream = new ByteArrayInputStream(cachedRequestAsBytes);
        }

        @Override
        public boolean isFinished() {
            try {
                return cachedBodyInputStream.available() == 0;
            } catch (IOException e) {
                log.error("[JUNO-SDK] Error checking available stream during cache", e);
            }
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int read() throws IOException {
            return cachedBodyInputStream.read();
        }
    }
}
