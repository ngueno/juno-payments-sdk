package com.ngueno.juno.sdk.resources.subscriptions.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;
import com.ngueno.juno.sdk.resources.base.model.SubscriptionStatus;
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubscriptionResource extends JunoBaseResource {

    private String id;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_TIME_PATTERN)
    private LocalDateTime createdOn;
    private Integer dueDay;
    private SubscriptionStatus status;
    private LocalDate startsOn;
    private LocalDate nextBillingDate;

}
