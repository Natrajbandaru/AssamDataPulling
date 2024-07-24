package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="drc01_items_dtreply")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drc01ItemsDtreply {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String dtreply_id;
	
	
	@Column(name="itemname",length = 9999)
	public String itemname;
	
	@Column(name="refid",length = 9999)
	public String refid;
	
	

	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dtreplydata_id",referencedColumnName = "dtreplydata_id")
	public  Drc01ItemsDtreplyDtreplydata dtreplydata;
	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   

}
