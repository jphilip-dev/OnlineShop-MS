package com.jphilip.onlineshop.auth.repository;

import com.jphilip.onlineshop.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
