package net.javaguides.springboot.Drc07Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name="drco7_main")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc07Main {
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String drc07_main_id;
	
	@Column(name="trdnm",length = 9999)
	public String trdnm;
	
	@Column(name="demandid",length = 9999)
	public String demandid;
	
	@Column(name="stcd",length = 9999)
	public String stcd;
	
	@Column(name="createdBy",length = 9999)
	public String createdBy;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="asmtorder_id",referencedColumnName = "asmtorder_id")
    public Drc07Asmtorder asmtorder;
	
	@Column(name="gstin",length = 9999)
	public String gstin;
	
	@Column(name="legnm",length = 9999)
	public String legnm;
	
	@Column(name="demanddt",length = 9999)
	public String demanddt;
	
	@Column(name="ipParams",length = 9999)
	public String path_params;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="todtls_id",referencedColumnName = "todtls_id")
    public Drc07Todtls todtls;

}
