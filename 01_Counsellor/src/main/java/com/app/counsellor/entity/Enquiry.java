package com.app.counsellor.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Setter
@Getter
@Table(name="enquiries")
public class Enquiry {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer enqId;
	
	private String stuName;
	private String stuPhno;
	private String classMode;
	private String courseName;
	private String enqStatus;
	
	@CreationTimestamp
	private LocalDate createDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="counsellor_id")
	private Counsellor counsellor;
	

}
