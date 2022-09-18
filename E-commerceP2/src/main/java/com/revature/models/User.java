package com.revature.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", nullable=false)
	private int id;
	@Column(name="user_name", nullable=false)
	private String userName;
	@Column(name="first_name", nullable=false)
	private String firstName;
	@Column(name="last_name", nullable=false)
	private String lastName;
	@Column(name="street_address", nullable=false)
	private String address;
	@Column(name="city", nullable=false)
	private String city;
	@Column(name="zip_code", nullable=false)
	private int zipcode;
	@Column(name="phone_number", nullable=false)
	private String phoneNumber;
	@Column(name="email", nullable=false)
	private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	@Column(name="user_role", nullable=false)
	private UserRole userRole;
	@Column(name="date_created", nullable=false)
	private LocalDate dateCreated;
	@Column(name="date_modified", nullable=false)
	private LocalDate dateModified;
	
	
	public User(String userName, String firstName, String lastName, String address, String city, int zipcode,
			String phoneNumber, String email, UserRole userRole, LocalDate dateCreated, LocalDate dateModified) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userRole = userRole;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	
	
}
