package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.RolesDTO;
import com.example.springbootecommerce.model.Roles;
import com.example.springbootecommerce.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RolesRepository rolesRepository;

    public List<RolesDTO> roleList()
    {
        List<Roles> roles= rolesRepository.findAll();
        List<RolesDTO> rolesDTOS =new ArrayList<>();
        for (Roles role:roles)
        {
            RolesDTO rolesDTO =new RolesDTO();
            rolesDTO.setId(role.getId());
            rolesDTO.setName(role.getName());
            rolesDTOS.add(rolesDTO);
        }
        return rolesDTOS;
    }


    public Roles addRole(Roles roles)
    {
        return rolesRepository.save(roles);
    }

    public List<Roles> getByRoleName(String roleName)
    {
        return rolesRepository.findByName(roleName);
    }
}
