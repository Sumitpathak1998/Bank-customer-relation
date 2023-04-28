package com.many.to.many.bi.directional.dao;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.many.to.many.bi.directional.dto.Bank;
import com.many.to.many.bi.directional.dto.Customer;

public class BankDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sumit"); 
	
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	
	EntityTransaction entityTransaction = entityManager.getTransaction(); 
	
	
	/*
	 * Insert Scenario 
	 * New Bank Data and List of New Customer Data Inserted 
	 * Method work Successfully
	 */
	public void saveBankCustomer( Bank bank , List<Customer> customer ){
		
		bank.setCustomer(customer);
		
		entityTransaction.begin();
		
		for ( Customer customer2 : customer) 
		{
			entityManager.persist(customer2);			
		}
		
		entityManager.persist(bank);
		entityTransaction.commit();
		
		System.out.println("-----Data saved -----");
	}
	/*
	 * Insert Scenario 
	 * New Bank Data and List of Existing customer Data Inserted 
	 * Method work Successfully
	 */
	public void saveBankCustomer( Bank bank , int customerId ) {
		
		Customer customer = entityManager.find( Customer.class, customerId); 
		
		List<Customer> list = new ArrayList<Customer>(); 
		
		if ( customer != null ) {
			
			list.add(customer); 
			
			bank.setCustomer(list);
			
			entityTransaction.begin();
			entityManager.persist(bank);
			entityTransaction.commit();
			
			System.out.println("--------- Data Inserted---------");
		}else {
			System.out.println("Please check the Customer Id");
		}
	}
	/*
	 * Insert Scenario 
	 * Existing Bank data i want to add more customer 
	 * Method not work , But work with  
	 */
	public void saveBankCustomer( int bankId , List<Customer> cusId) {
		
		Bank bank = entityManager.find( Bank.class, bankId); 
		 
		bank.setCustomer(cusId);
		
		entityTransaction.begin();
		entityManager.merge(bank);
		entityTransaction.commit();
		
		System.out.println("Bank and customer relation established");
	}
	
	/*
	 * GetById 
	 * Both Bank and customer data received successfully
	 */
	public Bank getById( int bankId) {
	
		Bank bank = entityManager.find( Bank.class, bankId); 
		
		return bank ; 
	}
	
	/*
	 * Display Method 
	 */
	public List<Bank> displayBankCustomer(){
		
		String select = "select s from Bank s";
		
		Query query = entityManager.createQuery(select); 
		
		List<Bank> list = query.getResultList(); 
		
		return list; 
	}
	/*
	 * Method for Delete the Data 
	 * Data deleted from Bank and bank_customer
	 * Customer table data not deleted 
	 */
	public void deleteBankCustomer( int bankId) {
		
		Bank bank = entityManager.find( Bank.class, bankId); 
		
		if ( bank != null) {
		
			entityTransaction.begin();
			entityManager.remove(bank);
			entityTransaction.commit();
			
			System.out.println("Data Deleted");
			
		}else {
			System.out.println("Please check the enter Bank Id");
		}
	}
	/*
	 * Method for Delete the Data 
	 * Data deleted from Bank and bank_customer
	 * Customer table data also deleted 
	 */
	public void deleteBanksCustomer( int bankId) {
		
		Bank bank = entityManager.find( Bank.class, bankId); 
		
		if ( bank != null) {
		
			entityTransaction.begin();
			
			List<Customer> list = bank.getCustomer();
			
			for ( Customer customer : list) {
				
				entityManager.remove(customer);
			}

			entityManager.remove(bank);
			entityTransaction.commit();
			
			System.out.println("Data Deleted");
			
		}else {
			System.out.println("Please check the enter Bank Id");
		}
	}
	/*
	 * Update Method
	 * 
	 */
	public void updateBankCustomer( int bankId , int cusId , String ifsc , long account ) {
		
		Bank bank = entityManager.find( Bank.class, bankId); 
		
		if ( bank != null ) {
			
			bank.setIfscCode(ifsc);
			
			entityTransaction.begin();
			
			List<Customer> customer = bank.getCustomer(); 
			
			for ( Customer customer2 : customer) {
				
				if (customer2.getCoustomerId() == cusId ) {
					
					customer2.setAccountNumber(account);
				}
			}
			entityManager.merge(bank); 
			entityTransaction.commit();
			
			System.out.println("--------Data Updated------");
		}
	}
}
