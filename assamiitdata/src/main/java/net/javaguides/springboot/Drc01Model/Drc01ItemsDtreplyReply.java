package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Table(name="drc01_items_dtreply_reply")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drc01ItemsDtreplyReply {
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String reply_id;
	
	@Column(name="reason",length = 9999)
	public String reason;
	
	@Column(name="replyty",length = 9999)
	public String replyty;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="reply_id",referencedColumnName = "reply_id")
	public List<Drc01ItemsDtreplyReplyMaindocs> maindocs; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="decdtls_id",referencedColumnName = "decdtls_id")
	public Drc01ItemsDtreplyReplyDecdtls decdtls; 
	
	@Column(name="fy",length = 9999)
	public String fy;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="reply_id",referencedColumnName = "reply_id")
	public List<Drc01ItemsDtreplyReplySuppdocs> suppdocs; 
	
	
	@Column(name="ntcdt",length = 9999)
	public String ntcdt;
	
	@Column(name="ntcno",length = 9999)
	public String ntcno;
	
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
