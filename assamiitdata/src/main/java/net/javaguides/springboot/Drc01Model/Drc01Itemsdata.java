package net.javaguides.springboot.Drc01Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.javaguides.springboot.Drc03Model.Drc03ItemsVpappdata;

@Entity
@Table(name="drc01_items")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drc01Itemsdata {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String item_id;

	@Column(name = "itemname",length = 9999)
	public String itemname;
	
	@Column(name = "refdt",length = 9999)
	public String refdt;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dtnoticedrc01data_id",referencedColumnName = "dtnoticedrc01data_id")
	public  Drc01ItemsDtnoticedrc01data  dtnoticedrc01data;
	
	
	@Column(name = "refid",length = 9999)
	public String refid;

    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="item_id",referencedColumnName = "item_id")
	public List<Drc01ItemsDtreply> dtreply; 
    
    @Column(name = "created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    createdAt = currentTime.withSecond(0).withNano(0);
	}
   
}
