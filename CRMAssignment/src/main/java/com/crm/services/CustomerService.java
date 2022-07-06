package com.crm.services;
import java.util.List;

import com.crm.model.*;
public interface CustomerService {
// Create Customer & Update
	public void saveCustomer(Customer customer);
//Search by Id 
	public Customer findById(int id);
// Delete Customer
	public void deleteById(int id);
//View All Customers
	public List<Customer> getAllCustomer();

}
