package net.javaguides.springboot.Drc03Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vpack_vpackdata_sdtls_acklgvp")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpackVpackdataSdtlsAcklgvp {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String acklgvp_id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="acklgvp_id",referencedColumnName = "acklgvp_id")
	public List<Drc03ItemsVpackVpackdataSdtlsAcklgvpMaindocs> maindocs;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="acklgvp_id",referencedColumnName = "acklgvp_id")
	public List<Drc03ItemsVpackVpackdataSdtlsAcklgvpAnnxdocs> annxdocs;
	
	
	public String type;
	
}

