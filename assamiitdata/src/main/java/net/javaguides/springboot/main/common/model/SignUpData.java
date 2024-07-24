package net.javaguides.springboot.main.common.model;

import java.time.LocalDateTime;


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
@Table(name="common_signUp_data")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class SignUpData {
	
	  
		@Id
		@GeneratedValue(generator = "uuid")
		@GenericGenerator(name = "uuid", strategy = "uuid2")
		public String signUpId;
		public String  name;
		public String  userName;
		public String password;
		

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;
	 
	     @PrePersist
	     protected void onCreate() {
	        createdAt = LocalDateTime.now();
	     }

}
