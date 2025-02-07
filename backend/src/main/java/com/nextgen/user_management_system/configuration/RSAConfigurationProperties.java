package com.nextgen.user_management_system.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RSAConfigurationProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}
