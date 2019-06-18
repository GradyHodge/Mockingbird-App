package com.tts.MockingBird.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.MockingBird.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByRole(String role);
}
