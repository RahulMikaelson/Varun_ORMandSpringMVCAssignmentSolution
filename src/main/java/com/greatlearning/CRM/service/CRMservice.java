package com.greatlearning.CRM.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.CRM.entity.Customers;

@Service
public interface CRMservice {
	public List<Customers> getCustomers();

	public void save(Customers customer);

	public Customers getCustomer(int id);

	public void deletebyId(int id);

}
