package com.ngueno.juno.sdk.resources.documents.model;

import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DocumentResource extends JunoBaseResource {

    private String id;
    private String type;
    private String description;
    private String approvalStatus;
}
