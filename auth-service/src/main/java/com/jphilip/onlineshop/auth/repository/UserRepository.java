package com.jphilip.onlineshop.auth.repository;

import com.jphilip.onlineshop.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
