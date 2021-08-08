package com.ngueno.juno.sdk.test;

public class FixtureHelper extends BaseFixtureHelper {

    public static final String X_SIGNATURE = "X-Signature";
    public static final String WEBHOOK_SECRET = "e3cb5c6f35f979acdb8ea2980a105b2e5669e86d91f7a1ca6fdb087369a5d640";
    public static final String EVENT_SIGNATURE = "8a2e26d02d4aed8c100ac6d0a72f73856616806e9ed5e705df2ec283f432fa33";

    public String getEventPayload() {
        return BaseFixtureHelper.getResource("events", "payment_notification.json");
    }
}
