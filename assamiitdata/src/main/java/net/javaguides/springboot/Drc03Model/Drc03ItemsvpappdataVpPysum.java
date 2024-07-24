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
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name="drc03_item_vappdata_vp_pysum")
public class Drc03ItemsvpappdataVpPysum {
 
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String pysum_id;
	public String paymentdate;
	public String rsn;
	public String scndt;
    public String prn;
    

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="lbltydtls_id",referencedColumnName = "lbltydtls_id")
	public Drc03ItemsvpappdataVpPysumLbltydtls lbltydtls;

	@Column(name = "created_at")
	 private LocalDateTime createdAt;
	 
   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }
	
}
