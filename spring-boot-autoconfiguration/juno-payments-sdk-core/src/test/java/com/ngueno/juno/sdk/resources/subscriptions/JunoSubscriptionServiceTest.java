package com.ngueno.juno.sdk.resources.subscriptions;

import static com.ngueno.juno.sdk.test.FixtureHelper.PLAN_ID;
import static com.ngueno.juno.sdk.test.FixtureHelper.SUBSCRIPTION_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.base.model.PlanFrequency;
import com.ngueno.juno.sdk.resources.base.model.PlanStatus;
import com.ngueno.juno.sdk.resources.base.model.SubscriptionStatus;
import com.ngueno.juno.sdk.resources.subscriptions.model.JunoPlanCreateRequest;
import com.ngueno.juno.sdk.resources.subscriptions.model.JunoSubscriptionCreateRequest;
import com.ngueno.juno.sdk.resources.subscriptions.model.PlanResource;
import com.ngueno.juno.sdk.resources.subscriptions.model.PlanResources;
import com.ngueno.juno.sdk.resources.subscriptions.model.SubscriptionResource;
import com.ngueno.juno.sdk.resources.subscriptions.model.SubscriptionResources;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

class JunoSubscriptionServiceTest extends AbstractSpringBootTest {

    @Test
    void createPlan() {
        JunoPlanCreateRequest request = new JunoPlanCreateRequest("dummyName", BigDecimal.valueOf(1000D));

        mockServerManager().expectPlanCreate(request);
        PlanResource resource = service.createPlan(request);

        assertPlan(resource);
    }

    @Test
    void listPlans() {
        mockServerManager().expectPlanList();
        PlanResources resources = service.listPlans();

        assertEquals(1, resources.getResources().size());
        assertPlan(resources.getResources().get(0));
    }

    @Test
    void findPlan() {
        mockServerManager().expectPlanFind(PLAN_ID);
        PlanResource resource = service.findPlan(PLAN_ID);

        assertPlan(resource);
    }

    @Test
    void activatePlan() {
        mockServerManager().expectPlanActivate(PLAN_ID);
        PlanResource resource = service.activatePlan(PLAN_ID);

        assertPlan(resource);
    }

    @Test
    void deactivatePlan() {
        mockServerManager().expectPlanDeactivate(PLAN_ID);
        PlanResource resource = service.deactivatePlan(PLAN_ID);

        assertPlan(resource);
    }

    @Test
    void createSubscription() {
        JunoSubscriptionCreateRequest request = new JunoSubscriptionCreateRequest(20, PLAN_ID, "dummyDescription",
                helper().createCreditCardDetailsWithCreditCardId(), helper().createChargeBilling());

        mockServerManager().expectSubscriptionCreate(request);
        SubscriptionResource resource = service.createSubscription(request);

        assertSubscription(resource);
    }

    @Test
    void listSubscriptions() {
        mockServerManager().expectSubscriptionList();
        SubscriptionResources resources = service.listSubscriptions();

        assertEquals(1, resources.getResources().size());
        assertSubscription(resources.getResources().get(0));
    }

    @Test
    void findSubscription() {
        mockServerManager().expectSubscriptionFind(SUBSCRIPTION_ID);
        SubscriptionResource resource = service.findSubscription(SUBSCRIPTION_ID);

        assertSubscription(resource);
    }

    @Test
    void activateSubscription() {
        mockServerManager().expectSubscriptionActivate(SUBSCRIPTION_ID);
        SubscriptionResource resource = service.activateSubscription(SUBSCRIPTION_ID);

        assertSubscription(resource);
    }

    @Test
    void deactivateSubscription() {
        mockServerManager().expectSubscriptionDeactivate(SUBSCRIPTION_ID);
        SubscriptionResource resource = service.deactivateSubscription(SUBSCRIPTION_ID);

        assertSubscription(resource);
    }

    @Test
    void cancelSubscription() {
        mockServerManager().expectSubscriptionCancelation(SUBSCRIPTION_ID);
        SubscriptionResource resource = service.cancelSubscription(SUBSCRIPTION_ID);

        assertSubscription(resource);
    }

    @Test
    void completeSubscription() {
        mockServerManager().expectSubscriptionCompletion(SUBSCRIPTION_ID);
        SubscriptionResource resource = service.completeSubscription(SUBSCRIPTION_ID);

        assertSubscription(resource);
    }

    private void assertPlan(PlanResource resource) {
        assertEquals("pln_D539CC5AF0E87FB1", resource.getId());
        assertEquals(parseDateTime("2020-06-22 07:22:18"), resource.getCreatedOn());
        assertEquals("Primeiro plano", resource.getName());
        assertEquals(PlanFrequency.MONTHLY, resource.getFrequency());
        assertEquals(PlanStatus.ACTIVE, resource.getStatus());
        assertEquals(BigDecimal.valueOf(100.01D), resource.getAmount());
        assertEquals("https://dummy-env/plans/pln_D539CC5AF0E87FB1", resource.getLinks("self").getHref());
    }

    private void assertSubscription(SubscriptionResource resource) {
        assertEquals("sub_EDA3F6CA13DFEC4C", resource.getId());
        assertEquals(parseDateTime("2020-06-22 07:26:48"), resource.getCreatedOn());
        assertEquals(21, resource.getDueDay());
        assertEquals(SubscriptionStatus.ACTIVE, resource.getStatus());
        assertEquals(parseDate("2020-07-21"), resource.getStartsOn());
        assertEquals(parseDate("2020-07-21"), resource.getNextBillingDate());
        assertEquals("https://dummy-env/subscriptions/sub_EDA3F6CA13DFEC4C", resource.getLinks("self").getHref());
    }

    @Resource
    private JunoSubscriptionService service;
}
