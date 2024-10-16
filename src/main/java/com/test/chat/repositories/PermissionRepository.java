package com.test.chat.repositories;

import com.test.chat.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findAllByRole(String role);
}
