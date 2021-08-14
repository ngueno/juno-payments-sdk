package com.ngueno.juno.sdk.client;

import java.io.File;

import com.ngueno.juno.sdk.resources.documents.JunoDocumentService;
import com.ngueno.juno.sdk.resources.documents.model.JunoDocumentFile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JunoSdkClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(JunoSdkClientApplication.class, args);

        JunoDocumentService service = ctxt.getBean(JunoDocumentService.class);

        //        service.uploadDocumentUsingEncryption("C2C2B71E7AEE09CDC043A80F6FDE5FA8DEC302C0A93E52489EFDC5A9B6D03E68", "doc_C0450B21FC0DDA26",
        //                new JunoDocumentFile(new File(
        //                        "C:\\Users\\ngueno\\Documents\\work\\git\\juno-payments-sdk\\spring-boot-autoconfiguration\\juno-payments-sdk-client\\src\\main\\resources\\sample_image.jpg")));
        service.uploadDocument("C2C2B71E7AEE09CDC043A80F6FDE5FA8DEC302C0A93E52489EFDC5A9B6D03E68", "doc_03525C8291DD809A",
                new JunoDocumentFile(new File(
                        "C:\\Users\\ngueno\\Documents\\work\\git\\juno-payments-sdk\\spring-boot-autoconfiguration\\juno-payments-sdk-client\\src\\main\\resources\\sample_image.jpg")));
    }

}
