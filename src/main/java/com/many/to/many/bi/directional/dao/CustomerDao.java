package com.many.to.many.bi.directional.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.many.to.many.bi.directional.dto.Bank;
import com.many.to.many.bi.directional.dto.Customer;

public class CustomerDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sumit"); 
	
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	
	EntityTransaction entityTransaction = entityManager.getTransaction(); 
	
	/*
	 * Insert Method 
	 * With New Customer and new Bank 
	 * Customer data Inserted and bank data also inserted 
	 * But bank_customer table relation not establish 
	 * 
	 */
	public void saveCustomer( Customer customer , List<Bank> bank ) {
		
		customer.setBank(bank);
		
		entityTransaction.begin();
		
		for ( Bank banks : bank) 
		{
			entityManager.persist(banks);	
		}		
		entityManager.persist(customer);
		entityTransaction.commit();
		
		System.out.println("-------Data Saved --------");
	}
	/*
	 * Only Customer Data added in the Customer table
	 * But relation not establish between bank and customer 
	 * But we maintain the relation with the help of bankDao method 3 
	 */
	public void saveCustomer( Customer customer , int bankId ) {
		
		Bank bank = entityManager.find( Bank.class , bankId); 
		
		List<Bank> list = new ArrayList<Bank>(); 
		
		if ( bank != null ) {
			
			list.add(bank); 
			
			customer.setBank(list);
			
			entityTransaction.begin();
			entityManager.persist(customer);
			entityTransaction.commit();
			
		}else {
			System.out.println("Check enter bank Id");
		}
	}
	/*
	 * Method for Update 
	 * Data not updated 
	 * From Customer side data not updated
	 */
	public void updateCustomerBank(int bankId , int cusId , String ifsc , long account) {
		
		Customer customer = entityManager.find( Customer.class, cusId);
		
		if ( customer != null ) {
			
			customer.setAccountNumber(account);
			
			List<Bank> list = customer.getBank(); 
			
			for ( Bank bank : list) {
				
				if ( bank.getBankId() == bankId) {
					
					bank.setIfscCode(ifsc);
				}
			}
			
			entityManager.merge(customer); 
			entityTransaction.commit();
			
			System.out.println("----------Data Updated -------");
		}
	}
}
