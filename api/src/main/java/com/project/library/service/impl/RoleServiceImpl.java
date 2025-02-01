package com.project.library.service.impl;

import com.project.library.DTO.RoleDTO;
import com.project.library.entity.Role;
import com.project.library.repository.RoleRepo;
import com.project.library.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Role convertToRoleService(RoleDTO roleDTO) {
        return Role.builder()
                .role_name(roleDTO.getRole_name())
                .build();
    }

    @Override
    public List<Role> findAllRolesService() {
        return roleRepo.findAll();
    }

    @Override
    public Role saveRoleService(RoleDTO roleDTO) {
        return roleRepo.save(convertToRoleService(roleDTO));
    }

    @Override
    public Role findRoleByIdService(Long id) {
        return roleRepo.findRoleById(id);
    }

    @Override
    public Role updateRoleService(RoleDTO roleDTO) {
        return roleRepo.save(convertToRoleService(roleDTO));
    }

    @Override
    public void deleteRoleService(Long id) {
        roleRepo.deleteRoleById(id);
    }
}
