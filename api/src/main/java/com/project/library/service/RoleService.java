package com.project.library.service;

import com.project.library.DTO.RoleDTO;
import com.project.library.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public interface RoleService {
    Role convertToRoleService(RoleDTO roleDTO);

    List<Role> findAllRolesService();

    Role saveRoleService(RoleDTO roleDTO);
    Role findRoleByIdService(Long id);
    Role updateRoleService(RoleDTO roleDTO);
    void deleteRoleService(Long id);
}
