package net.javaguides.springboot.main.common.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="daily_data_report")
@Data
public class DailyReportDataModel {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String id;
	
	@Column(name="url_name",length = 9999)
	public String urlName;
	
	@Column(name="parent_parames",length = 9999)
	public String parentParams;
	
	@Column(name="inserted_date",length = 9999)
	public Date insertedDate;

}
