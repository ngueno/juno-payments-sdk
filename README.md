# Juno Payments SDK

A Spring Boot autoconfiguration SDK for integrating with the Juno Payments API. This SDK provides a comprehensive set of services to interact with various Juno API endpoints, handling authentication, HTTP communication, and data serialization.

## Repository Structure

This repository is organized into multiple modules, each with a specific responsibility:

### Modules

- **`juno-payments-sdk-base`** - Base module providing core utilities, models, and shared functionality used across all other modules. Contains common configuration, error handling, and base HTTP service classes.

- **`juno-payments-sdk-core`** - Core module containing all API resource services. This module provides Spring components for interacting with Juno's API endpoints, including charges, payments, subscriptions, transfers, and more. It depends on the base module and provides the main business logic for API operations.

- **`juno-payments-sdk-webhooks`** - Webhook handling module that provides services for validating and processing webhook events from Juno. Includes event validation, webhook service management, and event handler services.

- **`juno-payments-sdk-starter`** - Starter module that combines the core and webhooks modules into a single dependency. This is the recommended module to include in your Spring Boot application for full SDK functionality.

- **`juno-payments-sdk-client`** - Client application module, typically used for testing and demonstration purposes.

- **`juno-payments-sdk-parent`** - Parent POM module that manages dependencies and build configuration for all child modules.

### Module Interactions

The modules interact with each other in a hierarchical structure:
- The **base** module provides foundational services to all other modules
- The **core** module uses the base module to implement API resource services
- The **webhooks** module extends functionality for event handling
- The **starter** module aggregates core and webhooks for easy consumption
- All modules inherit common configuration from the **parent** module

## API Core Operations

The SDK provides services organized by domain under the `/resources` path. Each service is a Spring `@Component` that can be autowired into your application. Here are the available operations:

### Additional Data (`JunoAdditionalDataService`)
- List banks
- List company types
- List business areas

### Balance (`JunoBalanceService`)
- Get account balance

### Bill Payments (`JunoBillPaymentService`)
- Pay bills

### Charges (`JunoChargeService`)
- Create charges
- List charges
- Find charge by ID
- Cancel charge
- Update charge split

### Credentials (`JunoCredentialsService`)
- Get public key

### Credit Cards (`JunoCreditCardService`)
- Tokenize credit cards

### Digital Accounts (`JunoDigitalAccountService`)
- Create digital account
- Update digital account
- Find digital account

### Documents (`JunoDocumentService`)
- List documents
- Get document by ID
- Upload document
- Upload document with encryption

### Notifications

#### Events (`JunoEventsService`)
- List event types

#### Webhooks (`JunoWebhookService`)
- Create webhook
- List webhooks
- Find webhook by ID
- Update webhook
- Remove webhook

### OAuth (`JunoOAuthService`, `ProxyJunoOAuthService`)
- OAuth token management

### Payments (`JunoPaymentService`)
- Create payments
- Refund payments
- Capture payments

### Subscriptions (`JunoSubscriptionService`)
- **Plans**: Create, list, find, activate, and deactivate plans
- **Subscriptions**: Create, list, find, activate, deactivate, cancel, and complete subscriptions

### Transfers (`JunoTransferService`)
- Create transfers (PIX, P2P, bank account transfers)

## SDK Configuration

The SDK uses Spring Boot's autoconfiguration mechanism. Simply add the dependency to your project and configure the required properties.

### Maven Dependency

Add the starter module to your `pom.xml`:

```xml
<dependency>
    <groupId>com.ngueno</groupId>
    <artifactId>juno-payments-sdk-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Gradle Dependency

Add to your `build.gradle`:

```gradle
dependencies {
    implementation 'com.ngueno:juno-payments-sdk-starter:0.0.1-SNAPSHOT'
}
```

### Configuration Properties

Configure the SDK in your `application.properties` or `application.yml` file:

#### Java - application.properties

```properties
# Credentials
juno.credentials.clientId=your-client-id
juno.credentials.clientSecret=your-client-secret
juno.credentials.resourceToken=your-resource-token
juno.credentials.publicToken=your-public-token  # Optional

# Endpoints
juno.base.url=https://sandbox.boletobancario.com
juno.auth-server.endpoint=${juno.base.url}/authorization-server
juno.auth-server.timeout=15
juno.apiv2.endpoint=${juno.base.url}/api-integration
juno.apiv2.timeout=15

# Webhooks (Optional)
juno.webhooks.endpoint.enabled=true
juno.webhooks.endpoint.request-mapping=/juno/events
juno.webhooks.main-webhook-service.enabled=true
juno.webhooks.main-webhook-service.secret=your-webhook-secret
```

#### Kotlin - application.yml

```yaml
juno:
  credentials:
    clientId: your-client-id
    clientSecret: your-client-secret
    resourceToken: your-resource-token
    publicToken: your-public-token  # Optional
  
  base:
    url: https://sandbox.boletobancario.com
  
  auth-server:
    endpoint: ${juno.base.url}/authorization-server
    timeout: 15
  
  apiv2:
    endpoint: ${juno.base.url}/api-integration
    timeout: 15
  
  webhooks:  # Optional
    endpoint:
      enabled: true
      request-mapping: /juno/events
    main-webhook-service:
      enabled: true
      secret: your-webhook-secret
```

### Usage Examples

#### Java Example

```java
import com.ngueno.juno.sdk.resources.charges.JunoChargeService;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    
    @Autowired
    private JunoChargeService chargeService;
    
    public void createCharge() {
        JunoChargeCreateRequest request = new JunoChargeCreateRequest();
        // Configure request...
        chargeService.createCharges(request);
    }
}
```

#### Kotlin Example

```kotlin
import com.ngueno.juno.sdk.resources.charges.JunoChargeService
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeCreateRequest
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val chargeService: JunoChargeService
) {
    fun createCharge() {
        val request = JunoChargeCreateRequest()
        // Configure request...
        chargeService.createCharges(request)
    }
}
```

### Environment-Specific Configuration

For different environments (development, staging, production), you can use Spring profiles:

**application-dev.properties:**
```properties
juno.base.url=https://sandbox.boletobancario.com
```

**application-prod.properties:**
```properties
juno.base.url=https://api.juno.com.br
```

Then activate the profile:
```bash
java -jar app.jar --spring.profiles.active=prod
```

Or in `application.properties`:
```properties
spring.profiles.active=dev
```

## Requirements

- Java 11 or higher
- Spring Boot 2.5.2 or higher

