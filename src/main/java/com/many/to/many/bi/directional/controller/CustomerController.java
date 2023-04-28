package com.many.to.many.bi.directional.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.many.to.many.bi.directional.dto.Bank;
import com.many.to.many.bi.directional.dto.Customer;
import com.many.to.many.bi.directional.service.CustomerService;

public class CustomerController {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		CustomerService service = new CustomerService(); 
		 
		while(true) {
			System.out.println("1.For Insert");
			int select = sc.nextInt();
			switch(select) {
			case 1:{
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
				
				List<Bank> list = new ArrayList<Bank>(); 
				char ch ; 
				do {
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
					
					list.add(bank); 
					
					System.out.println("If you want to Continoue to insert then Press y ");
					ch = sc.next().charAt(0);		
					}while(ch == 'y');
				
				service.saveCustomer(customer, list);
			}break ;
			case 2:{
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
				
				System.out.println("Enter the bank id from which customer added");
				int bankId = sc.nextInt(); 
				
				service.saveCustomer(customer, bankId);
			}break ; 
			case 3:{
				System.out.println("Enter the Bank Id");
				int bankId = sc.nextInt(); 
				System.out.println("Enter the Customer Id");
				int customerId = sc.nextInt(); 
				System.out.println("Enter the updated IFSC code ");
				String ifsc = sc.next(); 
				System.out.println("Enter the updated account number");
				long account = sc.nextLong();
				
				service.updateCustomerBank(bankId, customerId, ifsc, account);
			}break ; 
			case 4:{
				
			}break ; 
			}
		}
	}
}
