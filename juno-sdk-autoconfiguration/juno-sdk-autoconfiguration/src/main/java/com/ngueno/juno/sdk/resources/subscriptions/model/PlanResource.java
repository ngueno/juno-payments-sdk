package com.ngueno.juno.sdk.resources.subscriptions.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;
import com.ngueno.juno.sdk.resources.base.model.PlanFrequency;
import com.ngueno.juno.sdk.resources.base.model.PlanStatus;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PlanResource extends JunoBaseResource {

    private String id;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_TIME_PATTERN)
    private LocalDateTime createdOn;
    private String name;
    private PlanFrequency frequency;
    private PlanStatus status;
    private BigDecimal amount;

}
