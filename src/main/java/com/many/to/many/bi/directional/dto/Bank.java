package com.many.to.many.bi.directional.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

	@Id
	private int bankId ; 
	private String bankName ;
	private String address ; 
	private String ifscCode ;
	@ManyToMany 
	//@JoinColumn ( name = "customerId") 
	(cascade = CascadeType.ALL)
	private List<Customer> customer ;
	@Override
	public String toString() {
		System.out.println("-------Bank Details-------");
		return "bankId=" + bankId + "\nbankName =" + bankName + "\naddress=" + address + "\nifscCode=" + ifscCode
				+ "";
	}
	

	
}
