package net.javaguides.springboot.Drc03Model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vpack_vpackdata")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpackVpackdata {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String vpackdata_id;
	
	public String reason;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sdtls_id",referencedColumnName = "sdtls_id")
	public Drc03ItemsVpackVpackdataSdtls sdtls;
	

	@Embedded
	public Drc03ItemsVpackVpackdataTodtls todtls;
	
	
	
}
