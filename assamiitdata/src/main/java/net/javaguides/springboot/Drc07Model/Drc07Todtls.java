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
@Table(name="drco7_todtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc07Todtls {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String todtls_id;
	
	@Column(name="toid",length = 9999)
	public String toid;
	
	@Column(name="dg",length = 9999)
	public String dg;
	
	@Column(name="signty",length = 9999)
	public String signty;
	
	@Column(name="pn",length = 9999)
	public String pn;
	
	@Column(name="nm",length = 9999)
	public String nm;

}
