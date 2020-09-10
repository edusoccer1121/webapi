package com.core.webapi.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private Long Id;

    public Authority(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public Authority(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    @Override
    public String getAuthority() {
        return null;
    }
}
