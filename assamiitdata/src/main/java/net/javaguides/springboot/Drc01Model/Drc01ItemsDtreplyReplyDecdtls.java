package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="drc01_items_dtreply_reply_decdtls")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drc01ItemsDtreplyReplyDecdtls {
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String decdtls_id;
	
	@Column(name="dt",length = 9999)
	public String dt;

	@Column(name="signty",length = 9999)
	public String signty;
	
	@Column(name="asdes",length = 9999)
	public String asdes;
	
	@Column(name="pl",length = 9999)
	public String pl;
	
	@Column(name="asnm",length = 9999)
	public String asnm;
	
	@Column(name="pan",length = 9999)
	public String pan;

	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   
}
