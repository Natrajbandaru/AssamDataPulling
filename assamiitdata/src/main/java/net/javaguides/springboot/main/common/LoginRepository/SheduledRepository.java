package net.javaguides.springboot.main.common.LoginRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.main.common.model.SheduledData;

@Repository
public interface SheduledRepository extends JpaRepository<SheduledData, String> {
	
    int countByUrlkeyName(String urlkeyName);
    
    SheduledData findBysheduledId(String sheduledId);
    
    SheduledData findByUrlkeyName(String sheduledId);
    
    @Modifying
    @Query("UPDATE SheduledData e SET e.updatedDate = :updatedDate WHERE e.urlkeyName = :urlKeyName")
    int updateUrlDateByUrlKeyName(@Param("urlKeyName") String urlKeyName, @Param("updatedDate") String updatedDate);

} 