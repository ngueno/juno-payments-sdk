package com.ngueno.juno.sdk.resources.charges.model;

import static com.ngueno.juno.sdk.utils.CalendarUtils.formatDate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JunoChargeListRequest implements JunoBaseRequest {

    private LocalDate createdOnStart;
    private LocalDate createdOnEnd;
    private LocalDate dueDateStart;
    private LocalDate dueDateEnd;
    private LocalDate paymentDateStart;
    private LocalDate paymentDateEnd;
    private Boolean showUnarchived;
    private Boolean showArchived;
    private Boolean showDue;
    private Boolean showNotDue;
    private Boolean showPaid;
    private Boolean showNotPaid;
    private Boolean showCancelled;
    private Boolean showNotCancelled;
    private Boolean showManualReconciliation;
    private Boolean showNotManualReconciliation;
    private Boolean showNotFailed;
    private JunoChargeListOrder orderBy;
    private Boolean orderAsc;
    private Integer pageSize;
    private Integer page;
    private String firstObjectId;
    private String firstValue;
    private String lastObjectId;
    private String lastValue;

    @Getter
    @RequiredArgsConstructor
    public enum JunoChargeListOrder {
        ID("id"), DUE_DATE("dueDate"), AMOUNT("amount"), PAYMENT_DATE("paymentDate");

        private final String order;
    }

    public Map<String, Object> toQueryParamMap() {
        HashMap<String, Object> queryParams = new HashMap<>();

        queryParams.put("createdOnStart", formatDate(createdOnEnd));
        queryParams.put("createdOnEnd", formatDate(createdOnEnd));
        queryParams.put("dueDateStart", formatDate(dueDateStart));
        queryParams.put("dueDateEnd", formatDate(dueDateEnd));
        queryParams.put("paymentDateStart", formatDate(paymentDateStart));
        queryParams.put("paymentDateEnd", formatDate(paymentDateEnd));
        queryParams.put("showUnarchived", showUnarchived);
        queryParams.put("showArchived", showArchived);
        queryParams.put("showDue", showDue);
        queryParams.put("showNotDue", showNotDue);
        queryParams.put("showPaid", showPaid);
        queryParams.put("showNotPaid", showNotPaid);
        queryParams.put("showCancelled", showCancelled);
        queryParams.put("showNotCancelled", showNotCancelled);
        queryParams.put("showManualReconciliation", showManualReconciliation);
        queryParams.put("showNotManualReconciliation", showNotManualReconciliation);
        queryParams.put("showNotFailed", showNotFailed);
        queryParams.put("orderBy", orderBy);
        queryParams.put("orderAsc", orderAsc);
        queryParams.put("pageSize", pageSize);
        queryParams.put("page", page);
        queryParams.put("firstObjectId", firstObjectId);
        queryParams.put("firstValue", firstValue);
        queryParams.put("lastObjectId", lastObjectId);
        queryParams.put("lastValue", lastValue);

        return queryParams;
    }

}
