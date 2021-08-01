package com.ngueno.juno.sdk.resources.documents;

import static com.ngueno.juno.sdk.test.FixtureHelper.DOCUMENT_ID;
import static com.ngueno.juno.sdk.test.FixtureHelper.getMockDocumentPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import com.ngueno.juno.sdk.resources.documents.model.DocumentResource;
import com.ngueno.juno.sdk.resources.documents.model.DocumentResources;
import com.ngueno.juno.sdk.resources.documents.model.JunoDocumentFile;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

import org.junit.jupiter.api.Test;

class JunoDocumentServiceTest extends AbstractSpringBootTest {

    @Test
    void listDocuments() {
        mockServerManager().expectDocumentsList();

        DocumentResources resources = service.listDocuments();

        assertEquals(1, resources.getResources().size());
        assertDocument(resources.getResources().get(0));
    }

    @Test
    void getDocument() {
        mockServerManager().expectDocumentsGet(DOCUMENT_ID);

        DocumentResource resource = service.getDocument(DOCUMENT_ID);

        assertDocument(resource);
    }

    @Test
    void uploadDocument() {
        mockServerManager().expectDocumentsUpload(DOCUMENT_ID);

        JunoDocumentFile junoDocument = new JunoDocumentFile(getMockDocumentPath().toFile());
        DocumentResource resource = service.uploadDocument(DOCUMENT_ID, junoDocument);

        assertDocument(resource);
    }

    @Test
    void uploadDocumentUsingEncryption() {
        mockServerManager().expectCredentialsPublicKeyRequest();
        mockServerManager().expectDocumentsUploadUsingEncryption(DOCUMENT_ID);

        JunoDocumentFile junoDocument = new JunoDocumentFile(getMockDocumentPath().toFile());
        DocumentResource resource = service.uploadDocumentUsingEncryption(DOCUMENT_ID, junoDocument);

        assertDocument(resource);
    }

    private void assertDocument(DocumentResource resource) {
        assertEquals("doc_BECCACC6414F9333", resource.getId());
        assertEquals("SELFIE", resource.getType());
        assertEquals("Selfie", resource.getDescription());
        assertEquals("VERIFYING", resource.getApprovalStatus());
    }

    @Resource
    private JunoDocumentService service;
}
