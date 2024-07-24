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
@Table(name="drc01_items_dtnoticedrc01data_todtls")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc01ItemsDtnoticedrc01dataTodtls {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String todtls_id;
	
	@Column(name="toid",length = 9999)
	public String toid;
	
	@Column(name="dg",length = 9999)
	public String dg;
	
	@Column(name="pl",length = 9999)
	public String pl;
	
	@Column(name="pn",length = 9999)
	public String pn;
	
	@Column(name="nm",length = 9999)
	public String nm;
	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   

}
