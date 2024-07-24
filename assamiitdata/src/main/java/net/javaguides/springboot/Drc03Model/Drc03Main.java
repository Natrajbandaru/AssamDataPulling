package net.javaguides.springboot.Drc03Model;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="drco3_main")

public class Drc03Main {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	public String drco3_main_id;
	
	@Column(name="status_desc")
	public String statusDesc;
	
	@Column(name="casety")
    public String casety;
	
	@Column(name="dof")
    public String dof;
	
	@Column(name="gstin")
    public String gstin;
	
	@Column(name="state_cd")
    public String stateCd;
    
	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="drco3_main_id",referencedColumnName = "drco3_main_id")
    public List<Drc03Itemsdata> items;
	
	@Column(name="ipParams",length = 9999)
	public String path_params;
	

	 @Column(name = "created_at")
	 private LocalDateTime createdAt;
	 
    @PrePersist
    protected void onCreate() {
       createdAt = LocalDateTime.now();
    }

}
//{
//    "status_desc": "Acknowledged",
//    "casety": "ADJVP",
//    "dof": "2020-12-31",
//    "gstin": "18AACCN8384R1ZG",
//    "state_cd": "18",
//    "items": [
//        {
//            "vpappdata": {
//                "vp": {
//                    "pysum": {
//                        "paymentdate": "31/12/2020",
//                        "rsn": "INTEREST & TAX PAYMENT FOR F.Y 18-19 (ANNUAL RETURN)",
//                        "scndt": "",
//                        "prn": "IP1812200001584",
//                        "lbltydtls": {
//                            "act": [
//                                {
//                                    "intr": "4369",
//                                    "total": "4369",
//                                    "tx": "0",
//                                    "pos": "19",
//                                    "acttyp": "IGST",
//                                    "dbtno": "DC1812200092793",
//                                    "dbtdt": "2020-12-31",
//                                    "tp": {
//                                        "fromm": "APR",
//                                        "tom": "MAR",
//                                        "fromy": "2018",
//                                        "toy": "2019"
//                                    },
//                                    "pnlty": "0",
//                                    "others": "0",
//                                    "ldgrut": "Cash"
//                                },
//                                {
//                                    "intr": "1174",
//                                    "total": "2827",
//                                    "tx": "1653",
//                                    "pos": "",
//                                    "acttyp": "CGST",
//                                    "dbtno": "DC1812200092793",
//                                    "dbtdt": "2020-12-31",
//                                    "tp": {
//                                        "fromm": "APR",
//                                        "tom": "MAR",
//                                        "fromy": "2018",
//                                        "toy": "2019"
//                                    },
//                                    "pnlty": "0",
//                                    "others": "0",
//                                    "ldgrut": "Cash"
//                                },
//                                {
//                                    "intr": "1174",
//                                    "total": "2827",
//                                    "tx": "1653",
//                                    "pos": "",
//                                    "acttyp": "SGST",
//                                    "dbtno": "DC1812200092793",
//                                    "dbtdt": "2020-12-31",
//                                    "tp": {
//                                        "fromm": "APR",
//                                        "tom": "MAR",
//                                        "fromy": "2018",
//                                        "toy": "2019"
//                                    },
//                                    "pnlty": "0",
//                                    "others": "0",
//                                    "ldgrut": "Cash"
//                                }
//                            ]
//                        },
//                        "dcupdtls": [
//                            {}
//                        ],
//                        "sec": "74(5)",
//                        "cs": "Voluntary",
//                        "decdtls": {
//                            "dt": "31/12/2020",
//                            "signty": "",
//                            "asdes": "DIRECTOR",
//                            "pl": "SILCHAR",
//                            "asnm": "SUMATI NAHATA",
//                            "pan": "AADPN2257R"
//                        },
//                        "othsec": "",
//                        "fy": "2018-2019",
//                        "ovtp": {
//                            "frommonth": "APR",
//                            "tomonth": "MAR",
//                            "fromyear": "2018",
//                            "toyear": "2019"
//                        },
//                        "me": "24033989",
//                        "scnno": ""
//                    }
//                }
//            },
//            "itemname": "APPLICATION",
//            "refid": ""
//        },
//        {
//            "vpack": [
//                {
//                    "vpackdata": {
//                        "reason": "",
//                        "sdtls": {
//                            "acklgvp": {
//                                "maindocs": [
//                                    {
//                                        "dcupdtls": {
//                                            "ct": "application/pdf",
//                                            "docName": "DRC_03475016.pdf",
//                                            "ty": "ADJO",
//                                            "id": "202301099252227",
//                                            "hash": "73bd143a2d50e22e4db5bf98b888634e9d4c9812916fcb18e6cf89f1e4b93ba8"
//                                        }
//                                    },
//                                    {
//                                        "dcupdtls": {
//                                            "ct": "application/pdf",
//                                            "docName": "297352DRC-04.pdf",
//                                            "ty": "ADJO",
//                                            "id": "202301099252230",
//                                            "hash": "21775a75f64fbb114075e9b0134ed40b001669b05917014497a22d9849f84105"
//                                        }
//                                    }
//                                ],
//                                "suppdocs": [],
//                                "annxdocs": [
//                                    {
//                                        "dcupdtls": {
//                                            "ct": "application/pdf",
//                                            "docName": "DRC_03475016 - 2.pdf",
//                                            "ty": "ADJO",
//                                            "docttl": "TP_Share_Worksheets",
//                                            "id": "202301099252228",
//                                            "hash": "73bd143a2d50e22e4db5bf98b888634e9d4c9812916fcb18e6cf89f1e4b93ba8"
//                                        }
//                                    },
//                                    {
//                                        "dcupdtls": {
//                                            "ct": "application/pdf",
//                                            "docName": "DRC_03475016 - 3.pdf",
//                                            "ty": "ADJO",
//                                            "docttl": "TP_Uploaded_Worksheets",
//                                            "id": "202301099252229",
//                                            "hash": "73bd143a2d50e22e4db5bf98b888634e9d4c9812916fcb18e6cf89f1e4b93ba8"
//                                        }
//                                    }
//                                ],
//                                "type": "ISSUE ACKNOWLEDGEMENT"
//                            }
//                        },
//                        "todtls": {
//                            "dt": "19/07/2023",
//                            "toid": "10029111",
//                            "dg": "Superintendent",
//                            "signty": "DSC",
//                            "pl": "SILCHAR I RANGE",
//                            "pn": "",
//                            "nm": "Partha Pratim Das"
//                        }
//                    },
//                    "itemname": "ISSUE ACKNOWLEDGEMENT",
//                    "refdt": "19/07/2023",
//                    "refid": "ZO1807230204755"
//                }
//            ]
//        }
//    ],
//    "crn": "AD181220001833M"
//}