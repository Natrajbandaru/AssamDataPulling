package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Drc01Model.Drc01Main;

@Repository
public interface Drc01MainRepo extends JpaRepository<Drc01Main, String>{

}
