package com.ngueno.juno.sdk.utils;

import static com.ngueno.juno.sdk.test.FixtureHelper.getMockDocumentPath;
import static com.ngueno.juno.sdk.test.FixtureHelper.getResource;
import static java.nio.file.Files.newInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.ngueno.juno.sdk.model.error.JunoApiIntegrationException;
import com.ngueno.juno.sdk.resources.credentials.model.JunoPublicKey;
import com.ngueno.juno.sdk.test.AbstractTest;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class JweUtilsTest extends AbstractTest {

    @Test
    void encryptFile() {
        InputStream inputStream = null;
        JunoPublicKey junoPublicKey = getMockPublicKey();

        try {
            Path exampleDocument = getMockDocumentPath();
            inputStream = newInputStream(exampleDocument, StandardOpenOption.READ);

            byte[] encryptedDocument = JweUtils.encryptFile(junoPublicKey.getPublicKey(), exampleDocument.getFileName().toString(), inputStream);

            assertNotNull(encryptedDocument);
        } catch (IOException e) {
            fail("Failure during file encryption", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    @Test
    void encryptFileWithReadFailure() throws IOException {
        JunoPublicKey junoPublicKey = getMockPublicKey();
        Path exampleDocument = getMockDocumentPath();
        InputStream inputStream = mock(InputStream.class);

        when(inputStream.read(Mockito.any())).thenThrow(new IOException("dummyException"));

        try {
            JweUtils.encryptFile(junoPublicKey.getPublicKey(), exampleDocument.getFileName().toString(), inputStream);
            fail("This was supposed to throw integration exception :(");
        } catch (JunoApiIntegrationException e) {
            assertEquals("Failed to generate encrypted file", e.getMessage());
            assertTrue(e.getCause() instanceof IOException);
            assertEquals("dummyException", e.getCause().getMessage());
        }

        verify(inputStream).read(Mockito.any());
    }

    private JunoPublicKey getMockPublicKey() {
        return new JunoPublicKey(getResource("etc", "test-public-key.pub"));
    }

}
