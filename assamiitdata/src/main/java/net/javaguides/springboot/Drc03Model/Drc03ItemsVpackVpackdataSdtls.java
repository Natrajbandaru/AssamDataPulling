package net.javaguides.springboot.Drc03Model;

import javax.persistence.CascadeType;
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
@Table(name="drc03_item_vpack_vpackdata_sdtls")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpackVpackdataSdtls {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String sdtls_id;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="acklgvp_id",referencedColumnName = "acklgvp_id")
	public Drc03ItemsVpackVpackdataSdtlsAcklgvp acklgvp;
	
	
	
}
