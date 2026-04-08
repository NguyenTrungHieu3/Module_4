package com.example.customermanagementjpa.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.security.auth.login.AppConfigurationEntry;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfigurationEntry.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
