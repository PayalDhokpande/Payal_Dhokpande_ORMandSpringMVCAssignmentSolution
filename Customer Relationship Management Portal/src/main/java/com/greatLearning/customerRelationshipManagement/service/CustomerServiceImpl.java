package com.greatLearning.customerRelationshipManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatLearning.customerRelationshipManagement.entity.Customer;


@Repository
public class CustomerServiceImpl implements CustomerService {
	

		// create session
		private Session session;

		@Autowired
		CustomerServiceImpl(SessionFactory sessionFactory) {
			try {
				session = sessionFactory.getCurrentSession();
			} catch (HibernateException e) {
				session = sessionFactory.openSession();
			}

		}

		@Override
		@Transactional
		public List<Customer> findAll() {

			Transaction tx = session.beginTransaction();

			// find all the records from the database table
			List<Customer> customers = session.createQuery("from Customer").list();

			tx.commit();

			return customers;
		}

		@Override
		@Transactional
		public Customer findById(int id) {

			Customer customer = new Customer();

			Transaction tx = session.beginTransaction();

			// find record with Id from the database table
			customer = session.get(Customer.class, id);

			tx.commit();

			return customer;
		}

		@Override
		@Transactional
		public void save(Customer theCustomer) {

			Transaction tx = session.beginTransaction();

			// save transaction
			session.saveOrUpdate(theCustomer);

			tx.commit();

		}

		@Override
		@Transactional
		public void deleteById(int id) {

			Transaction tx = session.beginTransaction();

			// get transaction
			Customer customer = session.get(Customer.class, id);

			// delete record
			session.delete(customer);

			tx.commit();

		}

	}
