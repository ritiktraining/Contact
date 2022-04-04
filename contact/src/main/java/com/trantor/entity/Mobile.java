package com.trantor.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "MOBILE")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mobile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mobileId;

	private String mobileNumber;
	private String countryCd;
	private String type;
	private String createdBy;

	@CreationTimestamp
	private Date createdDate;

	private String updatedBy;

	@UpdateTimestamp
	private Date updatedDate;

}
