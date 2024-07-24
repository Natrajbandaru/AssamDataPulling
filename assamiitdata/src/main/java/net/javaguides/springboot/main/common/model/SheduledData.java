package net.javaguides.springboot.main.common.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="common_sheduled_data")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SheduledData {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String sheduledId;
	public String nameOfUrl;
	public String url;
	public String   sheduledDate;
	public String status;
	public String urlkeyName;
	public String   updatedDate;
	
	
	  @Column(name = "created_at")
	  private LocalDateTime createdAt;
	 
     @PrePersist
     protected void onCreate() {
        createdAt = LocalDateTime.now();
     }

	public void sheduledId(String string) {
		// TODO Auto-generated method stub
		
	}

}
