package com.revature.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="payment_id",nullable=false)
	private int id;
	@Column(name="payment_type",nullable=false)
	private String paymentType;
	@Column(name="payment_status",nullable=false)
	private String paymentStatus;
	@Column(name="payment_number",nullable=false)
	private String paymentNumber;
	@Column(name="payment_exp_date",nullable=false)
	private LocalDate paymentExpDate;
	@Column(name="payment_cvv",nullable=false)
	private int paymentCvv;
	@Column(name="payment_date_created",nullable=false)
	private LocalDate paymentDateCreated;
	@Column(name="payment_date_last_updated",nullable=false)
	private LocalDate paymentDateLastUpdated;
	@ManyToOne
	@JoinColumn(name="payment_user_id", referencedColumnName = "user_id")
	private User user;
	
}
