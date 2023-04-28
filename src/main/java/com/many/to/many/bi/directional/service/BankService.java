package com.many.to.many.bi.directional.service;

import java.util.List;

import com.many.to.many.bi.directional.dao.BankDao;
import com.many.to.many.bi.directional.dto.Bank;
import com.many.to.many.bi.directional.dto.Customer;

public class BankService {

	BankDao dao = new BankDao(); 
	
	public void saveBankCustomer( Bank bank , List<Customer> customer ) 
	{
		dao.saveBankCustomer(bank, customer);;
	}
	public void saveBankCustomer( Bank bank , int customerId ) 
	{
		dao.saveBankCustomer(bank, customerId);
	}
	public void saveBankCustomer( int bankId , List<Customer> cusId) 
	{
		dao.saveBankCustomer(bankId, cusId);
	}
	public Bank getById( int bankId) 
	{
		return dao.getById(bankId); 
	}
	public List<Bank> displayBankCustomer()
	{
		return dao.displayBankCustomer(); 
	}
	public void deleteBankCustomer( int bankId) 
	{
		dao.deleteBankCustomer(bankId);
	}
	public void deleteBanksCustomer( int bankId) 
	{
		dao.deleteBanksCustomer(bankId);
	}
	public void updateBankCustomer( int bankId , int cusId , String ifsc , long account )
	{
		dao.updateBankCustomer(bankId, cusId, ifsc, account);
	}
}
