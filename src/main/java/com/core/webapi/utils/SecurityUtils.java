package com.core.webapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static String getCurrentUser() {
        try {
            Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return (String) userDetails;
        } catch (Throwable t) {
            //logger.warn("No security context in session, using default 'unknown' user.");
        }
        return "unknown";
    }
}
