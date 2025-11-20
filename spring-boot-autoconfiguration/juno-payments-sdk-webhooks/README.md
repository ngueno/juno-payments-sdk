# Juno Payments SDK - Webhooks Module

This module provides webhook handling functionality for receiving and processing events from the Juno Payments API. It includes event validation, signature verification, and a flexible event handler system.

## Overview

The webhooks module enables your Spring Boot application to securely receive and process webhook events from Juno. It automatically validates event signatures, routes events to appropriate handlers, and provides a clean interface for implementing custom event processing logic.

## Features

- **Event Signature Validation**: Automatically validates webhook event signatures to ensure authenticity
- **Flexible Event Handling**: Register custom handlers for specific event types
- **REST Controller**: Auto-configured REST endpoint for receiving webhook events
- **Default Handler**: Fallback handler for unhandled event types
- **Configurable Webhook Secrets**: Support for multiple webhook secrets via `WebhookService` interface

## Dependencies

This module depends on:
- `juno-payments-sdk-base` - Base utilities and configuration

## Configuration

### Enable Webhook Endpoint

Add the following configuration to your `application.properties`:

```properties
# Enable webhook endpoint
juno.webhooks.endpoint.enabled=true
juno.webhooks.endpoint.request-mapping=/juno/events

# Enable main webhook service (optional)
juno.webhooks.main-webhook-service.enabled=true
juno.webhooks.main-webhook-service.secret=your-webhook-secret
```

### YAML Configuration

```yaml
juno:
  webhooks:
    endpoint:
      enabled: true
      request-mapping: /juno/events
    main-webhook-service:
      enabled: true
      secret: your-webhook-secret
```

## Usage

### Creating Custom Event Handlers

Implement the `JunoEventHandler` interface to handle specific event types:

```java
import com.ngueno.juno.sdk.resources.handler.JunoEventHandler;
import com.ngueno.juno.sdk.resources.handler.JunoEventWrapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentNotificationHandler implements JunoEventHandler {

    @Override
    public String getEventType() {
        return "PAYMENT_NOTIFICATION";
    }

    @Override
    public void handleEvent(JunoEventWrapper event) {
        // Process payment notification event
        // Access event data via event.getJsonNode()
    }
}
```

### Kotlin Example

```kotlin
import com.ngueno.juno.sdk.resources.handler.JunoEventHandler
import com.ngueno.juno.sdk.resources.handler.JunoEventWrapper
import org.springframework.stereotype.Component

@Component
class PaymentNotificationHandler : JunoEventHandler {
    
    override fun getEventType(): String = "PAYMENT_NOTIFICATION"
    
    override fun handleEvent(event: JunoEventWrapper) {
        // Process payment notification event
        val eventData = event.jsonNode
        // Your processing logic here
    }
}
```

### Custom Webhook Secret Service

If you need to support multiple webhook secrets or dynamic secret resolution, implement the `WebhookService` interface:

```java
import com.ngueno.juno.sdk.resources.validation.WebhookService;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CustomWebhookService implements WebhookService {
    
    @Override
    public String findWebhookSecret(Map<String, String[]> requestParams) {
        // Your logic to determine the webhook secret based on request parameters
        // For example, based on account ID, webhook ID, etc.
        return determineSecret(requestParams);
    }
}
```

## Architecture

### Components

- **`JunoEventHandlerController`**: REST controller that receives POST requests at the configured endpoint
- **`JunoEventValidationFilter`**: Servlet filter that validates event signatures before processing
- **`JunoEventValidationService`**: Service responsible for signature validation
- **`JunoEventHandlerService`**: Service that routes events to appropriate handlers
- **`JunoEventHandler`**: Interface for implementing custom event handlers
- **`DefaultJunoEventHandler`**: Default handler for unhandled event types
- **`WebhookService`**: Interface for custom webhook secret resolution

### Event Flow

1. Webhook event is received at the configured endpoint
2. `JunoEventValidationFilter` intercepts the request and validates the signature
3. If validation passes, the request proceeds to `JunoEventHandlerController`
4. `JunoEventHandlerService` routes the event to the appropriate handler based on event type
5. If no specific handler is found, the default handler processes the event

## Security

The module automatically validates webhook event signatures using HMAC-SHA256. The signature is verified against the webhook secret configured in your application. This ensures that events are authentic and have not been tampered with.

## Testing

The module includes comprehensive tests using MockServer for integration testing. Test utilities are available in the base module's test-jar.

## Requirements

- Java 11 or higher
- Spring Boot 2.5.2 or higher
- Servlet API (provided by Spring Boot)
