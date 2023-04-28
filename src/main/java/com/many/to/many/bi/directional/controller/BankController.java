package com.many.to.many.bi.directional.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.many.to.many.bi.directional.dto.Bank;
import com.many.to.many.bi.directional.dto.Customer;
import com.many.to.many.bi.directional.service.BankService;

public class BankController {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BankService service = new BankService();
		
		EntityManager entityManager = Persistence.createEntityManagerFactory("sumit").createEntityManager(); 
		
		while(true) {
			System.out.println("1.For Insert New Bank and Customer \n2.For Insert New Bank and Existing customer \n3.Insert more Customer on Existing Bank \n4.GetById \n5.For Display \n6.For Delete \n7.For Delete of Bank and Customer \n8.For Update the Data \n9.For Exit");
			int select = sc.nextInt(); 
			switch(select) {
			case 1:{
				System.out.println("Enter the Bank Id");
				int bankId = sc.nextInt(); 
				System.out.println("Enter the Bank name");
				String bankName = sc.next(); 
				System.out.println("Enter the address of the Bank");
				String address = sc.next(); 
				address += sc.nextLine(); 
				System.out.println("Enter the IFSC code");
				String ifsc = sc.next(); 
				
				Bank bank = new Bank(); 
				
				bank.setBankId(bankId);
				bank.setBankName(bankName);
				bank.setAddress(address);
				bank.setIfscCode(ifsc);
				
				char ch ;
				
				List<Customer> list = new ArrayList<Customer>(); 
				
				do {
					Customer customer = new Customer(); 
					
					System.out.println("Enter the Customer Id");
					int cusId = sc.nextInt(); 
					System.out.println("Enter the Customer Name");
					String cusName = sc.next(); 
					cusName += sc.nextLine();
					System.out.println("Enter the Account Number");
					long account = sc.nextLong(); 
					System.out.println("Enter the addhar Number");
					long addhar = sc.nextLong(); 
					System.out.println("Enter the Phone Number");
					long phone = sc.nextLong(); 
					
					customer.setCoustomerId(cusId);
					customer.setCustomerName(cusName);
					customer.setAccountNumber(account);
					customer.setAddhar(addhar);
					customer.setPhone(phone);
					
					list.add(customer); 
					
					System.out.println("If you want to Continoue to insert then Press y ");
					ch = sc.next().charAt(0); 
				}while(ch == 'y'); 
			
				service.saveBankCustomer(bank, list);
			}break ; 
			case 2:{
				System.out.println("Enter the Bank Id");
				int bankId = sc.nextInt(); 
				System.out.println("Enter the Bank name");
				String bankName = sc.next(); 
				System.out.println("Enter the address of the Bank");
				String address = sc.next(); 
				address += sc.nextLine(); 
				System.out.println("Enter the IFSC code");
				String ifsc = sc.next(); 
				
				Bank bank = new Bank(); 
				
				bank.setBankId(bankId);
				bank.setBankName(bankName);
				bank.setAddress(address);
				bank.setIfscCode(ifsc);
				
				System.out.println("Enter the customer Id");
				int cusId = sc.nextInt(); 
				
				service.saveBankCustomer(bank, cusId);
			}break ; 
			case 3:{
				System.out.println("Enter the Bank Id");
				int bankId = sc.nextInt();
				
				List<Customer> list = new ArrayList<Customer>();
				
				char ch ; 
				do {
					System.out.println("Enter the Customer Id");
					int cusId = sc.nextInt();
					
					Customer customer = entityManager.find( Customer.class, cusId);
					
					list.add(customer); 
					
					System.out.println("If you want to add more customer perss y");
					ch = sc.next().charAt(0);
				}while( ch == 'y'); 
				
				service.saveBankCustomer(bankId, list);
			}break ;
			case 4:{
				System.out.println("Enter the Bank Id");
				int id = sc.nextInt();
				
				Bank bank = service.getById(id); 
				
				System.out.println("---------Bank Details ------------");
				System.out.println("Bank Id : "+bank.getBankId());
				System.out.println("Bank Name : "+bank.getBankName());
				System.out.println("Bank Address : "+bank.getAddress());
				System.out.println("Bank IFSC Code : "+bank.getIfscCode());
				
				List<Customer> list = bank.getCustomer(); 
				
				for ( Customer customer : list) {
					
					System.out.println("------Customer Details --------");
					System.out.println("Customer Id : "+customer.getCoustomerId());
					System.out.println("Customer name : "+customer.getCustomerName());
					System.out.println("Account Number : "+customer.getAccountNumber());
					System.out.println("Addhar Number : "+customer.getAddhar());
					System.out.println("Customer Phone Number : "+customer.getPhone());
				}
			}break ; 
			case 5:{
				List<Bank> list = service.displayBankCustomer();
				
				for( Bank bank : list ) {
					
					System.out.println(bank);
					
					for ( Customer customer : bank.getCustomer()) {
						
						System.out.println(customer);
					}
				}
			}break ; 
			case 6:{
				System.out.println("Enter the bank Id for delete the data");
				int id = sc.nextInt();
				
				service.deleteBankCustomer(id);
			}break ; 
			case 7:{
				System.out.println("Enter the bank Id for delete the data");
				int id = sc.nextInt();
				
				service.deleteBanksCustomer(id);
			}break ; 
			case 8:{
				System.out.println("Enter the Bank Id");
				int bankId = sc.nextInt(); 
				System.out.println("Enter the Customer Id");
				int customerId = sc.nextInt(); 
				System.out.println("Enter the updated IFSC code ");
				String ifsc = sc.next(); 
				System.out.println("Enter the updated account number");
				long account = sc.nextLong(); 
				
				service.updateBankCustomer(bankId, customerId, ifsc, account);
			}break ;
			case 9:{
				System.out.println("Out of the Server");
				System.exit(0);
			}
			}
		}
	}
}
