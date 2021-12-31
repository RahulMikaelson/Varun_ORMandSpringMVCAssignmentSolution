package com.greatlearning.CRM.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.CRM.entity.Customers;

@Service
public class CRMserviceImpl implements CRMservice {

	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public CRMserviceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Customers> getCustomers() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Customers> customers = session.createQuery("from Customers").list();
		tx.commit();
		return customers;
	}

	@Transactional
	@Override
	public void save(Customers customer) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();

	}

	@Transactional
	@Override
	public void deletebyId(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Customers customer = session.get(Customers.class, id);
		session.delete(customer);
		tx.commit();

	}

	@Override
	public Customers getCustomer(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Customers customer = session.get(Customers.class, id);
		tx.commit();
		return customer;
	}

}
