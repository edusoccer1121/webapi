package com.core.webapi.config.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourcesServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String BASE_INVID_API_URL = "/api";
    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = BASE_INVID_API_URL + "/**";

    // UNSECURED PATHS
    private static final String ACCOUNTS_PATTERN = BASE_INVID_API_URL + "/accounts/*/email/*";
    private static final String ACTUATOR_PATTERN = "/actuator/**";
    private static final String LOGIN_PATTERN = BASE_INVID_API_URL + "/login";
    private static final String LOGIN_VALIDATE_PATTERN = LOGIN_PATTERN + "/validate";
    private static final String QUESTIONS_PATTERN = BASE_INVID_API_URL + "/questions";
    private static final String OUTAGE_SECTORS_PATTERN = BASE_INVID_API_URL + "/outage/sectors/*";
    private static final String PASSWORD_CHANGE_PATTERN = BASE_INVID_API_URL + "/password/change";
    private static final String PASSWORD_CHANGE_WITH_TOKEN_PATTERN = BASE_INVID_API_URL + "/password/changeWithToken";
    private static final String REGISTER_PATTERN = BASE_INVID_API_URL + "/customerAccounts/*/register";
    private static final String ADD_ACCOUNT_PATTERN = BASE_INVID_API_URL + "/customerAccounts/*/addAccount";
    private static final String RESET_PASSWORD_PATTERN = BASE_INVID_API_URL + "/email/*/resetPassword";
    private static final String SEND_EMAIL_PATTERN = BASE_INVID_API_URL + "/email/**/sendEmail";
    private static final String TOKEN_VALIDATE_PATTERN = BASE_INVID_API_URL + "/token/validate";
    private static final String USERS_PATTERN = BASE_INVID_API_URL + "/users/*";
    private static final String USERS_ACCOUNT_PATTERN = USERS_PATTERN + "/accounts/*";
    private static final String USERS_QUESTION_PATTERN = USERS_PATTERN + "/question";
    private static final String USERS_VALIDATE_ANSWER_PATTERN = USERS_PATTERN + "/validateAnswer";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID)
                .tokenServices(resourceTokenServices());
    }


    @Bean
    public TokenStore resourceTokenStore() {
        return new JwtTokenStore(resourceAccessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter resourceAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }


    @Bean
    public DefaultTokenServices resourceTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(resourceTokenStore());
        return defaultTokenServices;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()

                .authorizeRequests()
                .antMatchers(
                        ACCOUNTS_PATTERN,
                        ACTUATOR_PATTERN,
                        ADD_ACCOUNT_PATTERN,
                        LOGIN_PATTERN,
                        LOGIN_VALIDATE_PATTERN,
                        OUTAGE_SECTORS_PATTERN,
                        PASSWORD_CHANGE_PATTERN,
                        PASSWORD_CHANGE_WITH_TOKEN_PATTERN,
                        QUESTIONS_PATTERN,
                        REGISTER_PATTERN,
                        RESET_PASSWORD_PATTERN,
                        SEND_EMAIL_PATTERN,
                        TOKEN_VALIDATE_PATTERN,
                        USERS_PATTERN,
                        USERS_ACCOUNT_PATTERN,
                        USERS_QUESTION_PATTERN,
                        USERS_VALIDATE_ANSWER_PATTERN
                )
                .permitAll()
                .and()

                .requestMatchers()
                .antMatchers(SECURED_PATTERN)
                .and()

                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .antMatchers(HttpMethod.OPTIONS, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .anyRequest().access(SECURED_READ_SCOPE)
                .and()

        ;

    }
}
