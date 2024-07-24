package net.javaguides.springboot.Drc03Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vpack_vpackdata_sdtls_acklgvp_annxdocs_dcupdtls")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpackVpackdataSdtlsAcklgvpAnnxdocsDcupdtls {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String dcupdtls_id;
	public String ct;
	public String docName;
	public String ty;
	public String docttl;
	public String id;
	public String hash;
	
	
	
}
