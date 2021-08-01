package com.ngueno.juno.sdk.test.mockserver;

import static com.ngueno.juno.sdk.config.JunoApiHeaders.CONTENT_ENCODING;
import static com.ngueno.juno.sdk.config.JunoApiHeaders.CONTENT_ENCODING_GZIP;
import static com.ngueno.juno.sdk.test.FixtureHelper.MEDIA_TYPE_TEXT_PLAIN;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.MediaType.APPLICATION_JSON_UTF_8;
import static org.mockserver.model.MediaType.APPLICATION_OCTET_STREAM;
import static org.mockserver.model.MediaType.MULTIPART_FORM_DATA;
import static org.mockserver.model.StringBody.subString;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import com.ngueno.juno.sdk.config.JunoApiHeaders;
import com.ngueno.juno.sdk.resources.base.model.TransferType;
import com.ngueno.juno.sdk.resources.billpayments.model.JunoBillPaymentPayBillRequest;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeCreateRequest;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeSplitUpdateRequest;
import com.ngueno.juno.sdk.resources.creditcards.model.JunoCreditCardTokenizationRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookFindRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookRemoveRequest;
import com.ngueno.juno.sdk.resources.notifications.webhooks.model.JunoWebhookUpdateRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentCaptureRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentCreateRequest;
import com.ngueno.juno.sdk.resources.payments.model.JunoPaymentRefundRequest;
import com.ngueno.juno.sdk.resources.subscriptions.model.JunoPlanCreateRequest;
import com.ngueno.juno.sdk.resources.subscriptions.model.JunoSubscriptionCreateRequest;
import com.ngueno.juno.sdk.test.FixtureHelper;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.StringBody;

public final class MockServerManager extends AbstractExpectationConfigurer {

    private ClientAndServer mockServer;

    public MockServerManager(ClientAndServer mockServer) {
        this.mockServer = mockServer;
    }

    public ClientAndServer mockServer() {
        return mockServer;
    }

    public void configureDefaultExpectations() {
        expectOauthToken();
    }

    public void expectOauthToken() {
        mockServer.when( //
                request() //
                        .withMethod(POST.name()) //
                        .withPath("/auth-server/oauth/token") //
                        .withBody(StringBody.exact("grant_type=client_credentials")) //
                        .withContentType(FixtureHelper.MEDIA_TYPE_FORM_URLENCODED) //
                        .withHeader(new Header(JunoApiHeaders.AUTHORIZATION, FixtureHelper.BASIC_AUTHENTICATION)) //
        ) //
                .respond( //
                        response(getResource("oauth", "token", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectCreditCardTokenization(JunoCreditCardTokenizationRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/credit-cards/tokenization") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("creditcards", "tokenization", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectChargeCreate(JunoChargeCreateRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/charges") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("charges", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectChargeList() {
        expectChargeList("/charges");
    }

    public void expectChargeList(String uri) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath(uri)) //
                .respond( //
                        response(getResource("charges", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectChargeFind(String chargeId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/charges/" + chargeId)) //
                .respond( //
                        response(getResource("charges", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectChargeCancelation(String chargeId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(PUT.name()) //
                        .withPath("/charges/" + chargeId + "/cancelation")) //
                .respond( //
                        response().withStatusCode(204) //
                ); //
    }

    public void verifyChargeCancelation(String chargeId) {
        mockServer.verify( //
                getRequestExpectation() //
                        .withMethod(PUT.name()) //
                        .withPath("/charges/" + chargeId + "/cancelation") //
        ); //
    }

    public void expectChargeUpdateSplit(JunoChargeSplitUpdateRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(PUT.name()) //
                        .withPath("/charges/" + request.getChargeId() + "/split") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response().withStatusCode(204) //
                ); //
    }

    public void verifyChargeUpdateSplit(JunoChargeSplitUpdateRequest request) {
        mockServer.verify( //
                getRequestExpectation() //
                        .withMethod(PUT.name()) //
                        .withPath("/charges/" + request.getChargeId() + "/split") //
        ); //
    }

    public void expectPlanCreate(JunoPlanCreateRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/plans") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("plans", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPlanList() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/plans")//
        ) //
                .respond( //
                        response(getResource("plans", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPlanFind(String planId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/plans/" + planId) //
        ) //
                .respond( //
                        response(getResource("plans", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPlanActivate(String planId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/plans/" + planId + "/activation") //
        ) //
                .respond( //
                        response(getResource("plans", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPlanDeactivate(String planId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/plans/" + planId + "/deactivation") //
        ) //
                .respond( //
                        response(getResource("plans", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionCreate(JunoSubscriptionCreateRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/subscriptions") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("subscriptions", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionList() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/subscriptions")//
        ) //
                .respond( //
                        response(getResource("subscriptions", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionFind(String subscriptionId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/subscriptions/" + subscriptionId) //
        ) //
                .respond( //
                        response(getResource("subscriptions", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionActivate(String subscriptionId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/subscriptions/" + subscriptionId + "/activation") //
        ) //
                .respond( //
                        response(getResource("subscriptions", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionDeactivate(String subscriptionId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/subscriptions/" + subscriptionId + "/deactivation") //
        ) //
                .respond( //
                        response(getResource("subscriptions", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionCancelation(String subscriptionId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/subscriptions/" + subscriptionId + "/cancelation") //
        ) //
                .respond( //
                        response(getResource("subscriptions", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectSubscriptionCompletion(String subscriptionId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/subscriptions/" + subscriptionId + "/completion") //
        ) //
                .respond( //
                        response(getResource("subscriptions", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectBillPayment(JunoBillPaymentPayBillRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/bill-payments") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("billpayments", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectCredentialsPublicKeyRequest() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/credentials/public-key") //
        ) //
                .respond( //
                        response(getResource("credentials", "publickey", "GET.mock")) //
                                .withContentType(MEDIA_TYPE_TEXT_PLAIN) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPaymentCreate(JunoPaymentCreateRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/payments") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("payments", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPaymentRefund(JunoPaymentRefundRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/payments/" + request.getPaymentId() + "/refunds") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("payments", "id", "refunds", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPaymentCapture(JunoPaymentCaptureRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/payments/" + request.getPaymentId() + "/capture") //
                        .withBody(toJson(request)) //
        ) //
                .respond( //
                        response(getResource("payments", "id", "capture", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectBalance() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/balance") //
        ) //
                .respond( //
                        response(getResource("balance", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectP2pTransfer() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/transfers") //
                        .withBody(subString(TransferType.P2P.name()))) //
                .respond( //
                        response(getResource("transfers", "p2p", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectPixTransfer() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/transfers") //
                        .withBody(subString(TransferType.PIX.name()))) //
                .respond( //
                        response(getResource("transfers", "pix", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDefaultBankAccountTransfer() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/transfers") //
                        .withBody(subString(TransferType.DEFAULT_BANK_ACCOUNT.name()))) //
                .respond( //
                        response(getResource("transfers", "defaultbankaccount", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectBankAccountTransfer() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/transfers") //
                        .withBody(subString(TransferType.BANK_ACCOUNT.name()))) //
                .respond( //
                        response(getResource("transfers", "bankaccount", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectListEventTypes() {
        mockServer.when( //
                getRequestExpectationWithoutResourceToken() //
                        .withMethod(GET.name()) //
                        .withPath("/notifications/event-types")) //
                .respond( //
                        response(getResource("notifications", "events", "eventtypes", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectWebhookCreate() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/notifications/webhooks")) //
                .respond( //
                        response(getResource("notifications", "webhooks", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectWebhookList() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/notifications/webhooks")) //
                .respond( //
                        response(getResource("notifications", "webhooks", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectWebhookUpdate(JunoWebhookUpdateRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(PATCH.name()) //
                        .withPath("/notifications/webhooks/" + request.getWebhookId())) //
                .respond( //
                        response(getResource("notifications", "webhooks", "id", "PATCH.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectWebhookRemove(JunoWebhookRemoveRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(DELETE.name()) //
                        .withPath("/notifications/webhooks/" + request.getWebhookId())) //
                .respond( //
                        response(getResource("notifications", "webhooks", "id", "DELETE.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(204) //
                ); //
    }

    public void expectWebhookFind(JunoWebhookFindRequest request) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/notifications/webhooks/" + request.getWebhookId())) //
                .respond( //
                        response(getResource("notifications", "webhooks", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDigitalAccountCreateIndividual() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/digital-accounts")) //
                .respond( //
                        response(getResource("digitalaccounts", "individual", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDigitalAccountUpdateIndividual() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(PATCH.name()) //
                        .withPath("/digital-accounts")) //
                .respond( //
                        response(getResource("digitalaccounts", "individual", "PATCH.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDigitalAccountFindIndividual() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/digital-accounts")) //
                .respond( //
                        response(getResource("digitalaccounts", "individual", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDigitalAccountCreateCompany() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(POST.name()) //
                        .withPath("/digital-accounts")) //
                .respond( //
                        response(getResource("digitalaccounts", "company", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDigitalAccountUpdateCompany() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(PATCH.name()) //
                        .withPath("/digital-accounts")) //
                .respond( //
                        response(getResource("digitalaccounts", "company", "PATCH.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDigitalAccountFindCompany() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/digital-accounts")) //
                .respond( //
                        response(getResource("digitalaccounts", "company", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectAdditionalDataBanksList() {
        mockServer.when( //
                getRequestExpectationWithoutResourceToken() //
                        .withMethod(GET.name()) //
                        .withPath("/data/banks")) //
                .respond( //
                        response(getResource("additionaldata", "banks", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectAdditionalDataCompanyTypesList() {
        mockServer.when( //
                getRequestExpectationWithoutResourceToken() //
                        .withMethod(GET.name()) //
                        .withPath("/data/company-types")) //
                .respond( //
                        response(getResource("additionaldata", "companytypes", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectAdditionalDataBusinessAreasList() {
        mockServer.when( //
                getRequestExpectationWithoutResourceToken() //
                        .withMethod(GET.name()) //
                        .withPath("/data/business-areas")) //
                .respond( //
                        response(getResource("additionaldata", "businessareas", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDocumentsList() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/documents")) //
                .respond( //
                        response(getResource("documents", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDocumentsFind() {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/documents")) //
                .respond( //
                        response(getResource("documents", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDocumentsGet(String documentId) {
        mockServer.when( //
                getRequestExpectation() //
                        .withMethod(GET.name()) //
                        .withPath("/documents/" + documentId)) //
                .respond( //
                        response(getResource("documents", "id", "GET.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDocumentsUpload(String documentId) {
        mockServer.when( //
                getRequestExpectation(MULTIPART_FORM_DATA) //
                        .withMethod(POST.name()) //
                        .withPath("/documents/" + documentId + "/files"))//
                .respond( //
                        response(getResource("documents", "id", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }

    public void expectDocumentsUploadUsingEncryption(String documentId) {
        mockServer.when( //
                getRequestExpectation(APPLICATION_OCTET_STREAM) //
                        .withMethod(POST.name()) //
                        .withHeader(new Header(CONTENT_ENCODING, CONTENT_ENCODING_GZIP)) //
                        .withPath("/documents/" + documentId + "/files"))//
                .respond( //
                        response(getResource("documents", "id", "POST.mock")) //
                                .withContentType(APPLICATION_JSON_UTF_8) //
                                .withStatusCode(200) //
                ); //
    }
}
