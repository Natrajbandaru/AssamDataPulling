package net.javaguides.springboot.Drc03Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name="drc03_items_data")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Drc03Itemsdata {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String item_id;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "vp_app_data_id" , insertable = false, updatable = false)
//	public Drc03ItemsVpappdata vpappdata ;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vp_app_data_id")
	public Drc03ItemsVpappdata vpappdata ;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "vp_app_data_id" , insertable = false, updatable = false)
//	public Drc03ItemsVpappdata vpappdatamfy;
	

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id",referencedColumnName = "item_id")
	public List<Drc03ItemsVpack> vpack;

	

	@Column(name = "created_at")
	 private LocalDateTime createdAt;
	 
   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }
	
}
