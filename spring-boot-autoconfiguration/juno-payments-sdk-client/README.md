# Juno Payments SDK - Client Module

The client module is a sample Spring Boot application that demonstrates how to use the Juno Payments SDK. It serves as a reference implementation and can be used for testing and development purposes.

## Overview

This module provides:
- A complete Spring Boot application example
- Demonstrations of SDK usage patterns
- Reference implementation for common use cases
- Testing and development environment setup

## Purpose

The client module is designed to:
- **Demonstrate SDK Usage**: Show how to integrate and use the SDK in a real application
- **Provide Examples**: Reference implementations for common scenarios
- **Enable Testing**: Quick way to test SDK functionality
- **Development Tool**: Useful for development and debugging

## Structure

The module includes:
- Main Spring Boot application class
- Example service implementations
- Configuration examples
- Sample resources

## Usage

### Running the Application

The client module can be run as a standalone Spring Boot application:

```bash
cd spring-boot-autoconfiguration/juno-payments-sdk-client
mvn spring-boot:run
```

### Configuration

Configure the application using `application.properties`:

```properties
# SDK Configuration
juno.credentials.clientId=your-client-id
juno.credentials.clientSecret=your-client-secret
juno.credentials.resourceToken=your-resource-token

juno.base.url=https://sandbox.boletobancario.com
juno.auth-server.endpoint=${juno.base.url}/authorization-server
juno.apiv2.endpoint=${juno.base.url}/api-integration

# Webhook Configuration
juno.webhooks.endpoint.enabled=true
juno.webhooks.endpoint.request-mapping=/juno/events
```

### Building

Build the application:

```bash
mvn clean package
java -jar target/juno-payments-sdk-client-0.0.1-SNAPSHOT.jar
```

## Dependencies

The client module depends on:
- `juno-payments-sdk-starter` - Complete SDK functionality
- Spring Boot Web - For REST endpoints
- Spring Boot Starter - Core Spring Boot functionality

## Use Cases

This module is useful for:

1. **Learning**: Understanding how to integrate the SDK
2. **Testing**: Quick testing of SDK features
3. **Development**: Development and debugging environment
4. **Prototyping**: Rapid prototyping of integrations
5. **Reference**: Reference implementation for your own applications

## Customization

You can customize the client module to:
- Add your own REST controllers
- Implement custom business logic
- Add additional endpoints
- Integrate with your own services

## Requirements

- Java 11 or higher
- Spring Boot 2.5.2 or higher
- Maven 3.6+ (for building)

## Note

This module is primarily intended for:
- Development and testing
- Reference implementation
- SDK demonstration

For production applications, use the `juno-payments-sdk-starter` module directly in your own Spring Boot application rather than extending this client module.

