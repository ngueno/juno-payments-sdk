package com.ngueno.juno.sdk.resources.subscriptions;

import org.springframework.stereotype.Component;

import com.ngueno.juno.sdk.resources.base.http.JunoBaseService;
import com.ngueno.juno.sdk.resources.subscriptions.model.JunoPlanCreateRequest;
import com.ngueno.juno.sdk.resources.subscriptions.model.JunoSubscriptionCreateRequest;
import com.ngueno.juno.sdk.resources.subscriptions.model.PlanResource;
import com.ngueno.juno.sdk.resources.subscriptions.model.PlanResources;
import com.ngueno.juno.sdk.resources.subscriptions.model.SubscriptionResource;
import com.ngueno.juno.sdk.resources.subscriptions.model.SubscriptionResources;

@Component
public class JunoSubscriptionService extends JunoBaseService {

    private static final String PLANS = "/plans";
    private static final String PLANS_ID = "/plans/{id}";
    private static final String SUBSCRIPTIONS = "/subscriptions";
    private static final String SUBSCRIPTIONS_ID = "/subscriptions/{id}";

    public PlanResource createPlan(JunoPlanCreateRequest request) {
        return createPlan(getResourceToken(), request);
    }

    public PlanResource createPlan(String resourceToken, JunoPlanCreateRequest request) {
        return http().post(PLANS, resourceToken, request, PlanResource.class);
    }

    public PlanResources listPlans() {
        return listPlans(getResourceToken());
    }

    public PlanResources listPlans(String resourceToken) {
        return http().get(PLANS, resourceToken, PlanResources.class);
    }

    public PlanResource findPlan(String planId) {
        return findPlan(getResourceToken(), planId);
    }

    public PlanResource findPlan(String resourceToken, String planId) {
        return http().get(expandId(PLANS_ID, planId), resourceToken, PlanResource.class);
    }

    public PlanResource activatePlan(String planId) {
        return activatePlan(getResourceToken(), planId);
    }

    public PlanResource activatePlan(String resourceToken, String planId) {
        return http().post(expandId(PLANS_ID + "/activation", planId), resourceToken, PlanResource.class);
    }

    public PlanResource deactivatePlan(String planId) {
        return deactivatePlan(getResourceToken(), planId);
    }

    public PlanResource deactivatePlan(String resourceToken, String planId) {
        return http().post(expandId(PLANS_ID + "/deactivation", planId), resourceToken, PlanResource.class);
    }

    public SubscriptionResource createSubscription(JunoSubscriptionCreateRequest request) {
        return createSubscription(getResourceToken(), request);
    }

    public SubscriptionResource createSubscription(String resourceToken, JunoSubscriptionCreateRequest request) {
        return http().post(SUBSCRIPTIONS, resourceToken, request, SubscriptionResource.class);
    }

    public SubscriptionResources listSubscriptions() {
        return listSubscriptions(getResourceToken());
    }

    public SubscriptionResources listSubscriptions(String resourceToken) {
        return http().get(SUBSCRIPTIONS, resourceToken, SubscriptionResources.class);
    }

    public SubscriptionResource findSubscription(String subscriptionId) {
        return findSubscription(getResourceToken(), subscriptionId);
    }

    public SubscriptionResource findSubscription(String resourceToken, String subscriptionId) {
        return http().get(expandId(SUBSCRIPTIONS_ID, subscriptionId), resourceToken, SubscriptionResource.class);
    }

    public SubscriptionResource activateSubscription(String subscriptionId) {
        return activateSubscription(getResourceToken(), subscriptionId);
    }

    public SubscriptionResource activateSubscription(String resourceToken, String subscriptionId) {
        return http().post(expandId(SUBSCRIPTIONS_ID + "/activation", subscriptionId), resourceToken,
                SubscriptionResource.class);
    }

    public SubscriptionResource deactivateSubscription(String subscriptionId) {
        return deactivateSubscription(getResourceToken(), subscriptionId);
    }

    public SubscriptionResource deactivateSubscription(String resourceToken, String subscriptionId) {
        return http().post(expandId(SUBSCRIPTIONS_ID + "/deactivation", subscriptionId), resourceToken,
                SubscriptionResource.class);
    }

    public SubscriptionResource cancelSubscription(String subscriptionId) {
        return cancelSubscription(getResourceToken(), subscriptionId);
    }

    public SubscriptionResource cancelSubscription(String resourceToken, String subscriptionId) {
        return http().post(expandId(SUBSCRIPTIONS_ID + "/cancelation", subscriptionId), resourceToken,
                SubscriptionResource.class);
    }

    public SubscriptionResource completeSubscription(String subscriptionId) {
        return completeSubscription(getResourceToken(), subscriptionId);
    }

    public SubscriptionResource completeSubscription(String resourceToken, String subscriptionId) {
        return http().post(expandId(SUBSCRIPTIONS_ID + "/completion", subscriptionId), resourceToken,
                SubscriptionResource.class);
    }
}
