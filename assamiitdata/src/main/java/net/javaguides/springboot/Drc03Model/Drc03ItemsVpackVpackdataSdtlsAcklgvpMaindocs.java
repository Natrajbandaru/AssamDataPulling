package net.javaguides.springboot.Drc03Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vpack_vpackdata_sdtls_acklgvp_maindocs")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpackVpackdataSdtlsAcklgvpMaindocs {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String maindocs_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="dcupdtls_id",referencedColumnName = "dcupdtls_id")
	public Drc03ItemsVpackVpackdataSdtlsAcklgvpMaindocsDcupdtls dcupdtls;
}
