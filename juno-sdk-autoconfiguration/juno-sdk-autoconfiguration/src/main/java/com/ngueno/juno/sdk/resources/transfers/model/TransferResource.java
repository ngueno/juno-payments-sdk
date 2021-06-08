package com.ngueno.juno.sdk.resources.transfers.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;
import com.ngueno.juno.sdk.resources.base.model.Recipient;
import com.ngueno.juno.sdk.resources.base.model.TransferStatus;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransferResource extends JunoBaseResource {

    private String id;
    private String digitalAccountId;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_TIME_PATTERN)
    private LocalDateTime creationDate;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_TIME_PATTERN)
    private LocalDateTime transferDate;
    private BigDecimal amount;
    private TransferStatus status;
    private Recipient recipient;

}
