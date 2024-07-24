package net.javaguides.springboot.Drc03Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name="drc03_item_vappdata_vp_pysum_lbltydtls_act")
public class Drc03ItemsvpappdataVpPysumLbltydtlsAct {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String lbltydtls_act_id;
	
	public String intr;
	public String total;
	public String tx;
	public String pos;
	public String acttyp;
	public String dbtno;
	public String pnlty;
	public String others;
	public String ldgrut;
	
	@Embedded
	public Drc03ItemsvpappdataVpPysumLbltydtlsActTp tp;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	 
   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }

}
