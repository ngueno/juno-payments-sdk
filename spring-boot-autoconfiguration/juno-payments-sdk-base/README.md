# Juno Payments SDK - Base Module

The base module provides foundational utilities, models, and shared functionality used across all other modules in the Juno Payments SDK. This module contains core abstractions, configuration, and common infrastructure components.

## Overview

This module serves as the foundation for the entire SDK, providing:
- Base request and response models
- Common configuration classes
- Error handling infrastructure
- Utility classes
- Shared constants and headers

## Features

- **Base Models**: Abstract base classes for requests and resources
- **Configuration**: Core Spring Boot autoconfiguration
- **Error Handling**: Standardized exception handling for API errors
- **Utilities**: Common utility classes for date/time, JSON processing, etc.
- **API Headers**: Constants for Juno API headers
- **Object Mapper**: Pre-configured Jackson ObjectMapper for JSON serialization

## Dependencies

This module has minimal dependencies:
- Spring Boot WebFlux (for reactive support)
- Apache Commons Lang3
- Apache Commons IO
- Apache Commons Collections4
- Lombok (optional, compile-time only)

## Key Components

### Base Models

- **`JunoBaseRequest`**: Interface marker for all API request objects
- **`JunoBaseResource`**: Abstract base class for all API response resources with HATEOAS link support
- **`JunoBaseEmbeddedResource`**: Base class for embedded resources in API responses
- **`Link`**: Model for HATEOAS links

### Configuration

- **`JunoSdkBaseConfig`**: Base configuration that imports core configs
- **`JunoSdkScanConfig`**: Component scanning configuration
- **`JunoObjectMapperConfig`**: Jackson ObjectMapper configuration with custom serialization settings

### Error Handling

- **`JunoApiIntegrationException`**: Custom exception for API integration errors
- **`JunoApiError`**: Model for API error responses

### Utilities

- **`CalendarUtils`**: Utilities for date and calendar operations
- **`JunoApiHeaders`**: Constants for Juno API HTTP headers
- **`JunoApiFormats`**: Constants for date/time and other format strings
- **`JunoApiEncryption`**: Encryption-related constants

## Usage

This module is typically not used directly by application developers. Instead, it's included as a transitive dependency when you use other SDK modules like `juno-payments-sdk-core` or `juno-payments-sdk-starter`.

However, if you need to extend the SDK or create custom components, you can use the base classes:

```java
import com.ngueno.juno.sdk.resources.base.resource.JunoBaseResource;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomResource extends JunoBaseResource {
    
    @JsonProperty("customField")
    private String customField;
    
    // Access HATEOAS links via inherited getLinks() method
}
```

## Test Support

The module provides a test-jar artifact that includes:
- **`AbstractTestSuite`**: Base test class for integration tests
- **`BaseFixtureHelper`**: Helper utilities for test fixtures
- **`JacksonUtils`**: Utilities for JSON testing

To use test utilities in your tests:

```xml
<dependency>
    <groupId>com.ngueno</groupId>
    <artifactId>juno-payments-sdk-base</artifactId>
    <type>test-jar</type>
    <scope>test</scope>
</dependency>
```

## Requirements

- Java 11 or higher
- Spring Boot 2.5.2 or higher

## Module Structure

```
juno-payments-sdk-base/
├── config/          # Configuration classes
├── model/           # Base models and error classes
├── resources/        # Base request/resource classes
└── utils/           # Utility classes
```

