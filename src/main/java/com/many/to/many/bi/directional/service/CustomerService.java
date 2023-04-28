package com.many.to.many.bi.directional.service;

import java.util.List;

import com.many.to.many.bi.directional.dao.CustomerDao;
import com.many.to.many.bi.directional.dto.Bank;
import com.many.to.many.bi.directional.dto.Customer;

public class CustomerService {

	CustomerDao dao = new CustomerDao(); 
	
	public void saveCustomer( Customer customer , List<Bank> bank ) 
	{
		dao.saveCustomer(customer,bank);	
	}
	public void saveCustomer( Customer customer , int bankId ) 
	{
		dao.saveCustomer(customer, bankId);
	}
	public void updateCustomerBank(int bankId , int cusId , String ifsc , long account) 
	{
		dao.updateCustomerBank(bankId, cusId, ifsc, account);
	}
}
