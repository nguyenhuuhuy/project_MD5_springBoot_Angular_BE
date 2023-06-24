package com.example.demo.service.role;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.service.IGenderService;

import java.util.Optional;

public interface IRoleService extends IGenderService<Role> {
    Optional<Role> findByName(RoleName name);
}
