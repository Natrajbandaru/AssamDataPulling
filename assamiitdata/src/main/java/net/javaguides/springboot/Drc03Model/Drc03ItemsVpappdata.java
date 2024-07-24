package net.javaguides.springboot.Drc03Model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vpappdata")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpappdata {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String item_vappadata_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="vp_id" ,referencedColumnName = "vp_id")
	public Drc03ItemsvpappdataVp vp;

	@Column(name = "created_at")
	 private LocalDateTime createdAt;
	 
   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }


}
