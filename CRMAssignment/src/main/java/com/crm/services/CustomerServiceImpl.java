package com.crm.services;

import java.util.List;

import javax.transaction.Transactional;

import com.crm.model.Customer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceImpl implements CustomerService {
	// Create session
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public void saveCustomer(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();

	}

	@Transactional
	public Customer findById(int id) {
		Transaction tx = session.beginTransaction();
		Customer tempCustomer = session.get(Customer.class, id);
		tx.commit();
		return tempCustomer;

	}

	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		Customer tempCustomer = session.get(Customer.class, id);
		if (tempCustomer != null) {
			session.delete(tempCustomer);
			tx.commit();
		}

	}

	@Transactional
	public List<Customer> getAllCustomer() {
		Transaction tx = session.beginTransaction();
		List<Customer> listCustomer = session.createQuery("from Customer").list();
		tx.commit();
		return listCustomer;
	}

	

}
