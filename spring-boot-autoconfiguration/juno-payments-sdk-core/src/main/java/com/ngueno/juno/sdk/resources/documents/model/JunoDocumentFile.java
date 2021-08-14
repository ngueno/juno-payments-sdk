package com.ngueno.juno.sdk.resources.documents.model;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
public class JunoDocumentFile implements Serializable {

    private static final long serialVersionUID = 8714589245466202128L;

    private transient InputStream inputStream;
    @Setter
    private String fileName;
    private File file;

    public JunoDocumentFile(InputStream documentInputStream, String fileName) {
        this.inputStream = documentInputStream;
        setFileName(fileName);
        validateInputStreamUsage();
    }

    public JunoDocumentFile(File file) {
        this.file = file;
        setFileName(file.getName());
        validateFileUsage();
    }

    private void validateInputStreamUsage() {
        if (!hasInputStream()) {
            throw new JunoApiIntegrationException("Document input stream must not be null");
        }

        if (!hasFileName()) {
            throw new JunoApiIntegrationException("Filename must not be null or empty");
        }
    }

    private void validateFileUsage() {
        if (!hasFile()) {
            throw new JunoApiIntegrationException("File must not be null");
        }
    }

    public boolean hasFile() {
        return file != null;
    }

    public boolean hasInputStream() {
        return inputStream != null;
    }

    public boolean hasFileName() {
        return StringUtils.isNotBlank(fileName);
    }

}
