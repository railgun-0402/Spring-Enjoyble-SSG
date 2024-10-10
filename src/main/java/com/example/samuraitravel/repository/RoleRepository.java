package com.example.samuraitravel.repository;

import com.example.samuraitravel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
