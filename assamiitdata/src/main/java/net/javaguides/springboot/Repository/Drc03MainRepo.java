package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Drc03Model.Drc03Main;

@Repository
public interface Drc03MainRepo extends JpaRepository<Drc03Main, String>{

}
