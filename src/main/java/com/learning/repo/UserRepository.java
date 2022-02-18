package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Register;



@Repository
public interface UserRepository extends JpaRepository<Register, Integer> {
	
	//custom jpa method:we will not write any def just only declaration
	Boolean existsByEmailAndPassword(String email,String password);

	Optional<Register> findByUsername(String username);
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
