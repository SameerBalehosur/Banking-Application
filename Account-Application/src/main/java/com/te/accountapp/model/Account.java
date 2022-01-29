package com.te.accountapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "User_Account_Details")
@Valid
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Account_Id")
	@NotNull(message = "Id should not be null")
	private Long accountId;
	@Column(name = "Name_of_Account_Holder")
	@NotNull(message = "Username field is required")
	private String accountHolderName;
	public enum gender {
		MALE, FEMALE, OTHERS;
	}
	@NotNull(message = "Choose any option")
	@Column(name = "Gender")
	@Enumerated(EnumType.STRING)
	private gender gender;
	@Column(name = "Address")
	@NotNull(message = "Address should not be null")
	private String address;
	@Column(name = "Account_Nominee")
	@NotNull(message = "Nominee fields required")
	private String nominee;
	@Column(name = "Current_Account_Balance ")
	@DecimalMin(value = "0.0", message = "It should not be negativel")
	private double currentAmount;
	public enum accountStatus{
		ACTIVE,INACTIVE;
	}
	@Column(name = "Account_Status")
	@NotNull(message ="Value should be Active or Inactive")
	@Enumerated(EnumType.STRING)
	private  accountStatus accountStatus ;
}
