package net.javaguides.springboot.Drc07Model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc07AsmtorderTp {
	
	public String fromm;
	public String tom;
	public String fromy;
	public String toy;

}
