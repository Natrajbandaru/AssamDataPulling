package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.javaguides.springboot.Drc03Model.Drc03ItemsvpappdataVpPysumLbltydtlsActTp;

@Entity
@Table(name="drc01_items_dtnoticedrc01data_sdtls_dtcn_dmddtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc01ItemsDtnoticedrc01dataSdtlsDtscnDmddtls {
	
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
	
	@Embedded
	public Drc01ItemsDtnoticedrc01dataSdtlsDtscnDmddtlsTp tp;
	
	@Column(name="dpnlty",length = 9999)
	public String dpnlty;
	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   
	

}
