package net.javaguides.springboot.main.common.LoginRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.main.common.model.SignUpData;

public interface SignUpRepository extends JpaRepository<SignUpData, String>{

	
}
