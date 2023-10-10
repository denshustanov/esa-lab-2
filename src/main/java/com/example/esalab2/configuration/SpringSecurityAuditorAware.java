package com.example.esalab2.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication() == null || !context.getAuthentication().isAuthenticated()) {
            return Optional.empty();
        }
        return Optional.of(context.getAuthentication().getName());
    }
}
