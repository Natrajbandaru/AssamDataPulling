package net.javaguides.springboot.Drc07Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.javaguides.springboot.Drc03Model.Drc03Itemsdata;

@Entity
@Table(name="drco7_asmtorder_gdssvcdtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc07AsmtorderGdssvcdtls {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String gdssvcdtls_id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="gdssvcdtls_id",referencedColumnName = "gdssvcdtls_id")
    public List<Drc07AsmtorderGdssvcdtlsBzgddtls> bzgddtls;

}
