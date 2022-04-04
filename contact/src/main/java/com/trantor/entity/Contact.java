package com.trantor.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "CONTACT")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer contactId;

	private String firstName;
	private String lastName;

	private String emailAddress;
	private String createdBy;

	@CreationTimestamp
	private Date createdDate;

	private String updatedBy;

	@UpdateTimestamp
	private Date updatedDate;

	private String isActive;

	@OneToMany(targetEntity = Mobile.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "contactId", referencedColumnName = "contactId")
	private List<Mobile> mobile;

}
