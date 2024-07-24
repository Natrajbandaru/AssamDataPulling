package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;
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
@Table(name="drc01_items_dtnoticedrc01data_sdtls_dtcn")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc01ItemsDtnoticedrc01dataSdtlsDtscn {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String dtscn_id;
	
	@Column(name="reason", length = 9999)
	public String reason;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="dtscn_id",referencedColumnName = "dtscn_id")
	public List<Drc01ItemsDtnoticedrc01dataSdtlsDtscnSuppdocs> suppdocs;
	
	
	@Column(name="type",length = 9999)
	public String type;
	
	@Column(name="facts", length = 9999)
	public String facts;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="dtscn_id",referencedColumnName = "dtscn_id")
	public List<Drc01ItemsDtnoticedrc01dataSdtlsDtscnDmddtls> dmddtls;
	
	
	@Column(name="sec",length = 9999)
	public String sec;
	
	@Column(name="grounds",length = 9999)
	public String grounds;
	
	
	@Column(name="fetr",length = 9999)
	public String fetr;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="dtscn_id",referencedColumnName = "dtscn_id")
	public List<Drc01ItemsDtnoticedrc01dataSdtlsDtscnMaindocs> maindocs;
	
	
	@Column(name="duedt",length = 9999)
	public String duedt;
	
	@Column(name="fy",length = 9999)
	public String fy;
	
	@Embedded
	public Drc01ItemsDtnoticedrc01dataSdtlsDtscnTpovl tpovl;
	
	@Column(name="pershrng",length = 9999)
	public String pershrng;
	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   
	

}
