package com.vesus.listener;


import org.springframework.data.domain.AuditorAware;

public class CustomAuditor implements AuditorAware<String> {
    private String username;
    private String userId;

    @Override
    public String getCurrentAuditor() {
        return this.username;
    }

    public CustomAuditor() {
        super();
    }

    public CustomAuditor(String username) {
        this.username = username;
    }

    public CustomAuditor(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }
}
