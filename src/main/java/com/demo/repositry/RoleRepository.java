package com.demo.repositry;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>{

	 Role findByRole(String roleName);
	Role findById(Long Id);
	List<Role> findByIsActiveTrue();

	
}
