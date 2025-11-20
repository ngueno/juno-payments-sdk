# Juno Payments SDK - Starter Module

The starter module is a convenience module that combines the core SDK functionality and webhook handling into a single dependency. This is the recommended module to include in your Spring Boot application for full SDK functionality.

## Overview

This module aggregates:
- **`juno-payments-sdk-core`**: All API resource services
- **`juno-payments-sdk-webhooks`**: Webhook event handling

By including this single dependency, you get complete access to all Juno Payments API operations and webhook processing capabilities.

## Purpose

The starter module simplifies dependency management by providing a single artifact that includes all essential SDK functionality. Instead of managing multiple dependencies, you only need to include the starter module.

## Usage

### Maven

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>com.ngueno</groupId>
    <artifactId>juno-payments-sdk-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Gradle

Add to your `build.gradle`:

```gradle
dependencies {
    implementation 'com.ngueno:juno-payments-sdk-starter:0.0.1-SNAPSHOT'
}
```

## What's Included

When you include the starter module, you automatically get:

### API Services
All services from the core module:
- Charge management
- Payment processing
- Subscription management
- Transfer operations
- Digital account management
- Document handling
- Webhook management
- And more...

### Webhook Handling
All webhook functionality from the webhooks module:
- Event signature validation
- Event routing to handlers
- REST endpoint for receiving events
- Custom event handler support

## Configuration

Configure both core and webhook features in your `application.properties`:

```properties
# Core SDK Configuration
juno.credentials.clientId=your-client-id
juno.credentials.clientSecret=your-client-secret
juno.credentials.resourceToken=your-resource-token

juno.base.url=https://sandbox.boletobancario.com
juno.auth-server.endpoint=${juno.base.url}/authorization-server
juno.apiv2.endpoint=${juno.base.url}/api-integration

# Webhook Configuration (Optional)
juno.webhooks.endpoint.enabled=true
juno.webhooks.endpoint.request-mapping=/juno/events
juno.webhooks.main-webhook-service.enabled=true
juno.webhooks.main-webhook-service.secret=your-webhook-secret
```

## Example Application

```java
import com.ngueno.juno.sdk.resources.charges.JunoChargeService;
import com.ngueno.juno.sdk.resources.handler.JunoEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// Use API services
@Component
class PaymentService {
    private final JunoChargeService chargeService;
    
    public PaymentService(JunoChargeService chargeService) {
        this.chargeService = chargeService;
    }
    
    // Your business logic using chargeService
}

// Handle webhook events
@Component
class MyEventHandler implements JunoEventHandler {
    @Override
    public String getEventType() {
        return "PAYMENT_NOTIFICATION";
    }
    
    @Override
    public void handleEvent(JunoEventWrapper event) {
        // Process event
    }
}
```

## When to Use This Module

Use the starter module when you need:
- ✅ Complete SDK functionality (API operations + webhooks)
- ✅ Simplified dependency management
- ✅ Standard Spring Boot application setup

If you only need specific functionality, you can use individual modules:
- Use `juno-payments-sdk-core` if you only need API operations
- Use `juno-payments-sdk-webhooks` if you only need webhook handling

## Requirements

- Java 11 or higher
- Spring Boot 2.5.2 or higher

## Module Dependencies

```
juno-payments-sdk-starter
├── juno-payments-sdk-core
│   └── juno-payments-sdk-base
└── juno-payments-sdk-webhooks
    └── juno-payments-sdk-base
```

