# Juno Payments SDK - Core Module

The core module contains all API resource services for interacting with the Juno Payments API. This module provides Spring components for charges, payments, subscriptions, transfers, digital accounts, and all other Juno API operations.

## Overview

This module is the heart of the SDK, providing:
- Complete API resource services for all Juno endpoints
- Spring Boot autoconfiguration
- HTTP client configuration (RestTemplate and WebClient)
- OAuth token management
- Credential and environment configuration

## Features

- **Complete API Coverage**: Services for all Juno API domains
- **Spring Integration**: All services are Spring `@Component` beans, ready for dependency injection
- **Dual HTTP Clients**: Support for both RestTemplate (blocking) and WebClient (reactive)
- **Automatic Configuration**: Zero-configuration setup with Spring Boot autoconfiguration
- **Resource Token Management**: Automatic handling of resource tokens for API authentication
- **Error Handling**: Standardized error handling with custom exceptions

## Dependencies

This module depends on:
- `juno-payments-sdk-base` - Base utilities and models
- Spring Boot WebFlux (for reactive HTTP support)
- JOSE4J (for JWE encryption support)
- Apache Commons libraries

## Available Services

The module provides services organized by domain:

### Payment Operations
- **`JunoChargeService`**: Create, list, find, cancel charges; update splits
- **`JunoPaymentService`**: Create, refund, and capture payments
- **`JunoBillPaymentService`**: Pay bills

### Account Management
- **`JunoDigitalAccountService`**: Create, update, and find digital accounts
- **`JunoBalanceService`**: Get account balance

### Subscriptions
- **`JunoSubscriptionService`**: Manage plans and subscriptions (create, list, find, activate, deactivate, cancel, complete)

### Transfers
- **`JunoTransferService`**: Create transfers (PIX, P2P, bank account)

### Documents
- **`JunoDocumentService`**: List, get, and upload documents (with optional encryption)

### Notifications
- **`JunoEventsService`**: List available event types
- **`JunoWebhookService`**: Create, list, find, update, and remove webhooks

### Additional Services
- **`JunoCredentialsService`**: Get public keys for encryption
- **`JunoCreditCardService`**: Tokenize credit cards
- **`JunoAdditionalDataService`**: List banks, company types, and business areas
- **`JunoOAuthService`**: OAuth token management

## Configuration

The module uses Spring Boot autoconfiguration. Configure it via `application.properties`:

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
```

## Usage

### Injecting Services

All services are Spring components and can be injected:

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

### Kotlin Example

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

### Using Resource Tokens

All services provide methods that use the default resource token (from configuration) and methods that accept a custom resource token:

```java
// Uses default resource token from configuration
chargeService.createCharges(request);

// Uses custom resource token
chargeService.createCharges("custom-resource-token", request);
```

## Architecture

### Configuration Classes

- **`JunoSdkCoreConfig`**: Main autoconfiguration class
- **`JunoCredentialsConfig`**: Configures API credentials
- **`JunoEnvironmentConfig`**: Configures API endpoints and timeouts
- **`JunoHttpConfig`**: Configures RestTemplate and WebClient

### Base Service

All resource services extend **`JunoBaseService`**, which provides:
- HTTP client access
- Resource token management
- URL path expansion utilities
- HATEOAS link parsing

## Testing

The module includes comprehensive integration tests using MockServer. Test mocks are available in `src/test/resources/mockserver/` organized by domain.

## Requirements

- Java 11 or higher
- Spring Boot 2.5.2 or higher

## Module Structure

```
juno-payments-sdk-core/
├── config/              # Spring Boot autoconfiguration
├── resources/            # API resource services
│   ├── additionaldata/
│   ├── balance/
│   ├── billpayments/
│   ├── charges/
│   ├── credentials/
│   ├── creditcards/
│   ├── digitalaccounts/
│   ├── documents/
│   ├── notifications/
│   ├── oauth/
│   ├── payments/
│   ├── subscriptions/
│   └── transfers/
└── utils/               # Utility classes
```

