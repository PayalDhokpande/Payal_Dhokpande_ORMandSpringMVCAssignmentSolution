package com.greatLearning.customerRelationshipManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.customerRelationshipManagement.entity.Customer;
import com.greatLearning.customerRelationshipManagement.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	

		@Autowired
		private CustomerService customerService;

		// add mapping for "/list"

		@RequestMapping("/list")
		public String listCustomers(Model theModel) {

			// get Student from db
			List<Customer> theCustomers = customerService.findAll();

			// add to the spring model
			theModel.addAttribute("customers", theCustomers);

			return "list-Customers";
		}

		@RequestMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {

			// create model attribute to bind form data
			Customer theCustomer = new Customer();

			theModel.addAttribute("Customer", theCustomer);

			return "Customer-form";
		}

		@RequestMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("customerid") int theId, Model theModel) {

			// get the Student from the service
			Customer theCustomer = customerService.findById(theId);

			// set Student as a model attribute to pre-populate the form
			theModel.addAttribute("Customer", theCustomer);

			// send over to our form
			return "Customer-form";
		}

		@PostMapping("/save")
		public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
				@RequestParam("lastName") String lastName, @RequestParam("email") String email)
				 {

			Customer theCustomer;
			if (id != 0) {
				theCustomer = customerService.findById(id);
				theCustomer.setFirstName(firstName);
				theCustomer.setLastName(lastName);
				theCustomer.setEmail(email);
				
				
			} else
				theCustomer = new Customer(id, firstName, lastName, email);
			// save the student
			customerService.save(theCustomer);

			// use a redirect to prevent duplicate submissions
			return "redirect:/customer/list";

		}

		@RequestMapping("/delete")
		public String delete(@RequestParam("customerid") int theId) {

			// delete the student
			customerService.deleteById(theId);

			// redirect to /student/list
			return "redirect:/customer/list";

		}

	}


