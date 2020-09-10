package com.core.webapi.services;

import com.core.webapi.model.Role;
import com.core.webapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public List<Role> getRoles()
    {
        List<Role> roles = this.roleRepository.findAll();
        return roles == null ? Collections.emptyList(): roles;
    }
}
