package com.project.library.repository;

import com.project.library.entity.Book;
import com.project.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    void deleteRoleById(Long id);
    Role findRoleById(Long id);
}
