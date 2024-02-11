package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
