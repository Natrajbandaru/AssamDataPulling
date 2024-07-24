package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
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

@Entity
@Table(name="drc01_items_dtnoticedrc01data_sdtls_dtcn_maindocs_dcupdtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc01ItemsDtnoticedrc01dataSdtlsDtscnMaindocsDcupdtls {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String dcupdtls_id;
	
	@Column(name="ct",length = 9999)
	public String ct;
	
	@Column(name="docName",length = 9999)
	public String docName;
	
	@Column(name="ty",length = 9999)
	public String ty;
	
	@Column(name="id",length = 9999)
	public String id;
	
	@Column(name="hash",length = 9999)
	public String hash;

	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   
}
