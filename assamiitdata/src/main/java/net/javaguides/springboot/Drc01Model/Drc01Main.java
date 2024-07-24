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
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="drc01_main")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drc01Main {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String drco1_main_id;
	
	@Column(name="status_desc",length = 9999)
	public String status_desc;
	
	@Column(name="casety",length = 9999)
	public String casety;
	
	@Column(name="dof",length = 9999)
	public String dof;
	
	@Column(name="gstin",length = 9999)
	public String gstin;
	
	
	@Column(name="state_cd",length = 9999)
	public String state_cd;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="drco1_main_id",referencedColumnName = "drco1_main_id")
	public List<Drc01Itemsdata> items; 
	
	
	@Column(name="crn",length = 9999)
	public String crn;
	
	@Column(name="ipParams",length = 9999)
	public String path_params;
	
	@Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;
	
	

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   
}
