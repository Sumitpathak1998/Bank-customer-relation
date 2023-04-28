package com.many.to.many.bi.directional.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	private int coustomerId ; 
	private String customerName ; 
	private long accountNumber ; 
	private long addhar ; 
	private long phone ;
	@ManyToMany
	//@JoinTable( name = "bankcustomer" , joinColumns =  @JoinColumn( name = "bankId ") ,  inverseJoinColumns = @JoinColumn( name = "customerId"))
	/*
	 * Same as join column we use joinTable  for give the name of the table which compiler automatic generated 
	 * And for giving the name of column we same use join column method here which we make independent class that is 
	 * came under the join column and dependent came under the inverse join column 
	 */
	(mappedBy = "customer")// give at top of independent object use for generate forgein key in only one table so that extra table will not generate 
	/*
	 * Here customer is dependent and bank is independent so we write mapped by on the above of bank object
	 * Cascade we use for persist the data in single time without persist the dependent object 
	 */
	private List<Bank> bank ;
	
	@Override
	public String toString() {
		System.out.println("--------Customer Id----------");
		return "coustomerId=" + coustomerId + "\ncustomerName=" + customerName + "\naccountNumber="
				+ accountNumber + "\naddhar=" + addhar + "\nphone=" + phone + "";
	} 
	
	
}
