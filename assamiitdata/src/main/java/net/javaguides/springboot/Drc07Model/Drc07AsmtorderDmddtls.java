package net.javaguides.springboot.Drc07Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="drco7_asmtorder_dmddtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc07AsmtorderDmddtls {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String dmddtls_id;
	
	@Column(name="dact",length = 9999)
	public String dact;
	
	@Column(name="dfees",length = 9999)
	public String dfees;
	
	@Column(name="pos",length = 9999)
	public String pos;
	
	@Column(name="trnovr",length = 9999)
	public String trnovr;
	
	@Column(name="dtax",length = 9999)
	public String dtax;
	
	@Column(name="dist",length = 9999)
	public String dist;
	
	@Column(name="dtot",length = 9999)
	public String dtot;
	
	@Column(name="dothers",length = 9999)
	public String dothers;
	
	@Column(name="dpnlty",length = 9999)
	public String dpnlty;
	
	@Column(name="tr",length = 9999)
	public String tr;

}
