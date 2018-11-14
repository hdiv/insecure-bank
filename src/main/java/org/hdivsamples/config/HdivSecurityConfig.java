package org.hdivsamples.config;

import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.ValidationConfigurer;
import org.hdiv.ee.config.annotation.EnableHdivWebSecurity;
import org.hdiv.ee.config.annotation.builders.SecurityConfigBuilder;
import org.hdiv.ee.config.annotation.configuration.HdivWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHdivWebSecurity
public class HdivSecurityConfig extends HdivWebSecurityConfigurerAdapter {

    @Override
    public void configure(final SecurityConfigBuilder builder) {

        builder.confidentiality(false).sessionExpired().homePage("/").loginPage("/login");
        builder.reuseExistingPageInAjaxRequest(true);
    }

    @Override
    public void addExclusions(final ExclusionRegistry registry) {

        registry.addUrlExclusions("/resources/.*");
        registry.addUrlExclusions("/", "/login", "/j_spring_security_logout").method("GET");
        registry.addUrlExclusions("/j_spring_security_check").method("POST");
    }

    @Override
    public void configureEditableValidation(final ValidationConfigurer validationConfigurer) {

        validationConfigurer.addValidation("/.*");
    }

}