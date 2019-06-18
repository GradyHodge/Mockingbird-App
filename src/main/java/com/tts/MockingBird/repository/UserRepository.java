package com.tts.MockingBird.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.MockingBird.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

}
