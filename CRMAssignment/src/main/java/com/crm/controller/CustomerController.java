package com.crm.controller;

import java.util.List;

import com.crm.model.Customer;
import com.crm.services.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	
	private CustomerService customerService;

	@RequestMapping("/listCustomer")
	public String listCustomer(Model model) {
		System.out.println("List of Customers");
		List<Customer> customers = customerService.getAllCustomer();
		model.addAttribute("Customer", customers);
		return "list-customers";
	}

	@RequestMapping("/formForAdd")
	public String formForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("Customer", customer);
		return "Customer-form";

	}

	@RequestMapping("/formForUpdate")
	public String formForUpdate(@RequestParam("id") int theId, Model model) {
		Customer customer = customerService.findById(theId);
		model.addAttribute("Customer", customer);
		return "Customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {
		System.out.println(id);
		Customer customer;
		if (id != 0) {
			customer = customerService.findById(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
		} else
			customer = new Customer(firstName, lastName, email);
		customerService.saveCustomer(customer);
		return "redirect:/customer/listCustomer";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		customerService.deleteById(id);
		return "redirect:/customer/listCustomer";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "exit";
	}
	

}
