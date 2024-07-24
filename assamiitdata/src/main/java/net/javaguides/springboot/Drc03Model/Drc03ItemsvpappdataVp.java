package net.javaguides.springboot.Drc03Model;

import java.time.LocalDateTime;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vappdata_vp")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsvpappdataVp {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String vp_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pysum_id",referencedColumnName = "pysum_id")
	public Drc03ItemsvpappdataVpPysum pysum;
	
//    @OneToOne(mappedBy = "vp", cascade = CascadeType.ALL)
//    private Drc03Itemsvpappdata drco3;

	@Column(name = "created_at")
	 private LocalDateTime createdAt;
	 
   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }
	
	
	
}
