package net.javaguides.springboot.main.common.LoginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.main.common.model.LoginData;

@Repository
public interface LoginRepository extends JpaRepository<LoginData , String> {
	
	LoginData findByUserName(String userName);
	
	LoginData findByPassword(String password);


}
