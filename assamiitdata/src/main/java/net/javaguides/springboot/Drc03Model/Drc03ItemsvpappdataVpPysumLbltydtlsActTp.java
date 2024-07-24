package net.javaguides.springboot.Drc03Model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsvpappdataVpPysumLbltydtlsActTp {

	
	public String fromm;
	public String tom;
	public String fromy;
	public String toy;
	
}
