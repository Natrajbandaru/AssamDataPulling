package net.javaguides.springboot.Drc07Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import lombok.ToString;
import net.javaguides.springboot.Drc03Model.Drc03Itemsdata;

@Entity
@Table(name="drco7_asmtorder")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc07Asmtorder {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String asmtorder_id;
	
	@Column(name="orddt",length = 9999)
	public String orddt;
	
	@Column(name="duedt",length = 9999)
	public String duedt;
	
	@Column(name="ordno",length = 9999)
	public String ordno;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="gdssvcdtls_id",referencedColumnName = "gdssvcdtls_id")
    public Drc07AsmtorderGdssvcdtls gdssvcdtls;
	
	@Column(name="cmt1",length = 9999)
	public String cmt1;
	
	
	@Embedded
	public Drc07AsmtorderTp tp;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="asmtorder_id",referencedColumnName = "asmtorder_id")
    public List<Drc07AsmtorderDmddtls> dmddtls;
	
	@Column(name="isuinv",length = 9999)
	public String isuinv;
	

}
