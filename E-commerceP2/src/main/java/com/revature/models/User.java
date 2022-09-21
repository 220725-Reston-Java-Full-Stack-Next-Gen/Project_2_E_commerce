package com.revature.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id", nullable=false)
	private int id;
	@Column(name="user_name", nullable=false, unique = true)
	private String userName;
	@Column(name="user_password", nullable=false)
	private String password;
	@Column(name="first_name", nullable=false)
	private String firstName;
	@Column(name="last_name", nullable=false)
	private String lastName;
	@Column(name="street_address", nullable=false)
	private String address;
	@Column(name = "state", nullable = false)
	private String state;
	@Column(name="city", nullable=false)
	private String city;
	@Column(name="zip_code", nullable=false)
	private int zipcode;
	@Column(name="phone_number", nullable=false)
	private String phoneNumber;
	@Column(name="email", nullable=false)
	private String email;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_role_id", referencedColumnName = "user_role_id", nullable=false)
	private UserRole userRole;
	@Column(name="date_created", nullable=false)
	private LocalDate dateCreated;
	@Column(name="date_modified")
	private LocalDate dateModified;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Payment> payment;
	
	public User(String userName, String password, String firstName, String lastName, String address, String city,
			int zipcode, String phoneNumber, String email, UserRole userRole, LocalDate dateCreated,
			LocalDate dateModified, Set<Payment> payment) {
		super();
		this.userName = userName;
		this.password = password;
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
		this.payment = payment;
	}
	
	
	
	
	
	
	
}
