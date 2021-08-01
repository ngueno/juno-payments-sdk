package com.ngueno.juno.sdk.resources.documents;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.PublicKey;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.credentials.JunoCredentialsService;
import com.ngueno.juno.sdk.resources.documents.model.DocumentResource;
import com.ngueno.juno.sdk.resources.documents.model.DocumentResources;
import com.ngueno.juno.sdk.resources.documents.model.JunoDocumentFile;
import com.ngueno.juno.sdk.utils.JweUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class JunoDocumentService extends JunoBaseService {

    private static final String DOCUMENTS = "/documents";
    private static final String DOCUMENTS_ID = DOCUMENTS + "/{id}";
    private static final String DOCUMENTS_ID_UPLOAD = DOCUMENTS + "/{id}/files";

    public DocumentResources listDocuments() {
        return listDocuments(getResourceToken());
    }

    public DocumentResources listDocuments(String resourceToken) {
        return http().get(DOCUMENTS, resourceToken, DocumentResources.class);
    }

    public DocumentResource getDocument(String documentId) {
        return getDocument(getResourceToken(), documentId);
    }

    public DocumentResource getDocument(String resourceToken, String documentId) {
        return http().get(expandId(DOCUMENTS_ID, documentId), resourceToken, DocumentResource.class);
    }

    public DocumentResource uploadDocument(String documentId, JunoDocumentFile file) {
        return uploadDocument(getResourceToken(), documentId, file);
    }

    public DocumentResource uploadDocument(String resourceToken, String documentId, JunoDocumentFile file) {
        return internalUploadDocument(resourceToken, documentId, file, false);
    }

    public DocumentResource uploadDocumentUsingEncryption(String documentId, JunoDocumentFile file) {
        return uploadDocumentUsingEncryption(getResourceToken(), documentId, file);
    }

    public DocumentResource uploadDocumentUsingEncryption(String resourceToken, String documentId, JunoDocumentFile file) {
        return internalUploadDocument(resourceToken, documentId, file, true);
    }

    private DocumentResource internalUploadDocument(String resourceToken, String documentId, JunoDocumentFile file, boolean encrypted) {
        InputStream inputStream = null;

        try {
            inputStream = getInputStream(file);
            if (encrypted) {
                return internalUploadEncrypted(resourceToken, documentId, file, inputStream);
            }
            return internalUpload(resourceToken, documentId, inputStream);
        } catch (IOException e) {
            throw new JunoApiIntegrationException("Failure during file upload, unable to read given file", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private DocumentResource internalUpload(String resourceToken, String documentId, InputStream inputStream) throws IOException {
        return http().upload(expandId(DOCUMENTS_ID_UPLOAD, documentId), resourceToken, read(inputStream), DocumentResource.class);
    }

    private DocumentResource internalUploadEncrypted(String resourceToken, String documentId, JunoDocumentFile file, InputStream inputStream) {
        return http().uploadEncrypted(expandId(DOCUMENTS_ID_UPLOAD, documentId), resourceToken, encrypt(resourceToken, file, inputStream),
                DocumentResource.class);
    }

    private byte[] read(InputStream inputStream) throws IOException {
        return IOUtils.readFully(inputStream, inputStream.available());
    }

    private byte[] encrypt(String resourceToken, JunoDocumentFile file, InputStream inputStream) {
        return JweUtils.encryptFile(getPublicKey(resourceToken), file.getFileName(), inputStream);
    }

    private InputStream getInputStream(JunoDocumentFile file) throws IOException {
        return file.hasInputStream() ? file.getInputStream() : Files.newInputStream(file.getFile().toPath(), StandardOpenOption.READ);
    }

    private PublicKey getPublicKey(String resourceToken) {
        return credentialsService.getPublicKey(resourceToken).getPublicKey();
    }

    @Resource
    private JunoCredentialsService credentialsService;
}
