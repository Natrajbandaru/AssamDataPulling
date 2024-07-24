package net.javaguides.springboot.Drc03Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="drc03_item_vappdata_vp_pysum_lbltydtls")
public class Drc03ItemsvpappdataVpPysumLbltydtls {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String lbltydtls_id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="lbltydtls_id", referencedColumnName = "lbltydtls_id")
	public List<Drc03ItemsvpappdataVpPysumLbltydtlsAct> act;

	@Column(name = "created_at")
	 private LocalDateTime createdAt;
	 
   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }
}
