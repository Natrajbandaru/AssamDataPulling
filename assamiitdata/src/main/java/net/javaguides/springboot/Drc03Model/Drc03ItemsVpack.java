package net.javaguides.springboot.Drc03Model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_item_vpack")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03ItemsVpack {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String item_vpack_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="vpackdata_id", referencedColumnName = "vpackdata_id")
	public Drc03ItemsVpackVpackdata vpackdata;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
	 
	@PrePersist
	protected void onCreate() {
	    createdAt = LocalDateTime.now();
	}

	
}
