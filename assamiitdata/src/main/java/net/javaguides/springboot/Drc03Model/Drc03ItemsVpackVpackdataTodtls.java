package net.javaguides.springboot.Drc03Model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpackVpackdataTodtls {

	public String dt;
	public String toid;
	public String dg;
	public String signty;
	public String pl;
	public String pn;
	public String nm;
	
	
	
}
