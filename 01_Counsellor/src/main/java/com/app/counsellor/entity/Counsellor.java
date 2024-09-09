package com.app.counsellor.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Counsellor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer counsellorId;
	
	private String name;
	
	@Column(unique=true)
	private String email;
	
	private String pwd;
	private Long phno;
	@CreationTimestamp
	private LocalDate createDate;
	@UpdateTimestamp
	private LocalDate updateDate;
	
	
	

	
	
	

}
