package com.project.library.controller;

import com.project.library.DTO.RoleDTO;
import com.project.library.entity.Role;
import com.project.library.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    @NonNull
    private final RoleService roleService;

    @GetMapping("/all")
    public List<Role> getAllRoles(){
        return roleService.findAllRolesService();
    }

    @PostMapping("/save")
    public Role saveRole(@RequestBody RoleDTO roleDTO){
        return roleService.saveRoleService(roleDTO);
    }

    @GetMapping("/getOne/{id}")
    public Role getRoleById(@PathVariable Long id){
        return roleService.findRoleByIdService(id);
    }

    @PutMapping("/update")
    public Role updateRoleById(@RequestBody RoleDTO roleDTO){
        return roleService.updateRoleService(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoleById(@PathVariable Long id){
        roleService.deleteRoleService(id);
    }

}
