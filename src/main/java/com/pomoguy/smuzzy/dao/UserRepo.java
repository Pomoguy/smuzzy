package com.pomoguy.smuzzy.dao;

import com.pomoguy.smuzzy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
