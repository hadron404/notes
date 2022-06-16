# Client Configuration In Spring-security#Client 



## How To Use?

### :zero: application.yml

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          messaging-client-oidc:
            provider: spring
            client-id: messaging-client
            client-secret: secret
            authorization-grant-type: authorization_code
            redirect-uri: "http://127.0.0.1:8080/login/oauth2/code/{registrationId}"
            scope: openid
            client-name: messaging-client-oidc
          messaging-client-authorization-code:
            provider: spring
            client-id: messaging-client
            client-secret: secret
            authorization-grant-type: authorization_code
            redirect-uri: "http://127.0.0.1:8080/authorized"
            scope: message.read,message.write
            client-name: messaging-client-authorization-code
          messaging-client-client-credentials:
            provider: spring
            client-id: messaging-client
            client-secret: secret
            authorization-grant-type: client_credentials
            scope: message.read,message.write
            client-name: messaging-client-client-credentials
        provider:
          spring:
             issuer-uri: http://localhost:9000

```



## How To Read

```java
@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class OAuth2ClientProperties implements InitializingBean {

	/**
	 * OAuth provider details.
	 */
	private final Map<String, Provider> provider = new HashMap<>();

	/**
	 * OAuth client registrations.
	 */
	private final Map<String, Registration> registration = new HashMap<>();

	public Map<String, Provider> getProvider() {
		return this.provider;
	}

	public Map<String, Registration> getRegistration() {
		return this.registration;
	}
    
       @Override
	public void afterPropertiesSet() {
		validate();
	}

	public void validate() {
		getRegistration().values().forEach(this::validateRegistration);
	}

	private void validateRegistration(Registration registration) {
		if (!StringUtils.hasText(registration.getClientId())) {
			throw new IllegalStateException("Client id must not be empty.");
		}
	}
    
       
	/**
	 * A single client registration.
	 */
	public static class Registration {

		/**
		 * Reference to the OAuth 2.0 provider to use. May reference an element from the
		 * 'provider' property or used one of the commonly used providers (google, github,
		 * facebook, okta).
		 */
		private String provider;

		/**
		 * Client ID for the registration.
		 */
		private String clientId;

		/**
		 * Client secret of the registration.
		 */
		private String clientSecret;

		/**
		 * Client authentication method. May be left blank when using a pre-defined
		 * provider.
		 */
		private String clientAuthenticationMethod;

		/**
		 * Authorization grant type. May be left blank when using a pre-defined provider.
		 */
		private String authorizationGrantType;

		/**
		 * Redirect URI. May be left blank when using a pre-defined provider.
		 */
		private String redirectUri;

		/**
		 * Authorization scopes. When left blank the provider's default scopes, if any,
		 * will be used.
		 */
		private Set<String> scope;

		/**
		 * Client name. May be left blank when using a pre-defined provider.
		 */
		private String clientName;
       }
    
       	public static class Provider {

		/**
		 * Authorization URI for the provider.
		 */
		private String authorizationUri;

		/**
		 * Token URI for the provider.
		 */
		private String tokenUri;

		/**
		 * User info URI for the provider.
		 */
		private String userInfoUri;

		/**
		 * User info authentication method for the provider.
		 */
		private String userInfoAuthenticationMethod;

		/**
		 * Name of the attribute that will be used to extract the username from the call
		 * to 'userInfoUri'.
		 */
		private String userNameAttribute;

		/**
		 * JWK set URI for the provider.
		 */
		private String jwkSetUri;

		/**
		 * URI that can either be an OpenID Connect discovery endpoint or an OAuth 2.0
		 * Authorization Server Metadata endpoint defined by RFC 8414.
		 */
		private String issuerUri;
        }
}
```

> @ConfigurationProperties 使用参见
>
> [Guide to @ConfigurationProperties in Spring Boot !](https://www.baeldung.com/configuration-properties-in-spring-boot)





## Analysis

:one: Registration

* `spring.security.oauth2.client.registration.xx` xx 为`registration`map的key,作为registrationId,
* `provider`,默认为registrationId

:two: Provider

## CommonOAuth2Provider



```java
public enum CommonOAuth2Provider {

	GOOGLE {

		@Override
		public Builder getBuilder(String registrationId) {
			ClientRegistration.Builder builder = getBuilder(registrationId,
					ClientAuthenticationMethod.CLIENT_SECRET_BASIC, DEFAULT_REDIRECT_URL);
			builder.scope("openid", "profile", "email");
			builder.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth");
			builder.tokenUri("https://www.googleapis.com/oauth2/v4/token");
			builder.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs");
			builder.issuerUri("https://accounts.google.com");
			builder.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo");
			builder.userNameAttributeName(IdTokenClaimNames.SUB);
			builder.clientName("Google");
			return builder;
		}

	},

	GITHUB {

		@Override
		public Builder getBuilder(String registrationId) {
			ClientRegistration.Builder builder = getBuilder(registrationId,
					ClientAuthenticationMethod.CLIENT_SECRET_BASIC, DEFAULT_REDIRECT_URL);
			builder.scope("read:user");
			builder.authorizationUri("https://github.com/login/oauth/authorize");
			builder.tokenUri("https://github.com/login/oauth/access_token");
			builder.userInfoUri("https://api.github.com/user");
			builder.userNameAttributeName("id");
			builder.clientName("GitHub");
			return builder;
		}

	},

	FACEBOOK {

		@Override
		public Builder getBuilder(String registrationId) {
			ClientRegistration.Builder builder = getBuilder(registrationId,
					ClientAuthenticationMethod.CLIENT_SECRET_POST, DEFAULT_REDIRECT_URL);
			builder.scope("public_profile", "email");
			builder.authorizationUri("https://www.facebook.com/v2.8/dialog/oauth");
			builder.tokenUri("https://graph.facebook.com/v2.8/oauth/access_token");
			builder.userInfoUri("https://graph.facebook.com/me?fields=id,name,email");
			builder.userNameAttributeName("id");
			builder.clientName("Facebook");
			return builder;
		}

	},

	OKTA {

		@Override
		public Builder getBuilder(String registrationId) {
			ClientRegistration.Builder builder = getBuilder(registrationId,
					ClientAuthenticationMethod.CLIENT_SECRET_BASIC, DEFAULT_REDIRECT_URL);
			builder.scope("openid", "profile", "email");
			builder.userNameAttributeName(IdTokenClaimNames.SUB);
			builder.clientName("Okta");
			return builder;
		}

	};

	private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

	protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method,
			String redirectUri) {
		ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
		builder.clientAuthenticationMethod(method);
		builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
		builder.redirectUri(redirectUri);
		return builder;
	}

	/**
	 * Create a new
	 * {@link org.springframework.security.oauth2.client.registration.ClientRegistration.Builder
	 * ClientRegistration.Builder} pre-configured with provider defaults.
	 * @param registrationId the registration-id used with the new builder
	 * @return a builder instance
	 */
	public abstract ClientRegistration.Builder getBuilder(String registrationId);

}

```

