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
@Table(name="drco7_asmtorder_gdssvcdtls_bzgddtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc07AsmtorderGdssvcdtlsBzgddtls {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String bzgddtls_id;
	

	@Column(name="gdes",length = 9999)
	public String gdes;
	
	@Column(name="hsncd",length = 9999)
	public String hsncd;
	

}
