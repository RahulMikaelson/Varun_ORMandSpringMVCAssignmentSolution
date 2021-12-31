package com.greatlearning.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.CRM.entity.Customers;
import com.greatlearning.CRM.service.CRMservice;

@Controller
@RequestMapping("/Customers")
public class CRMcontroller {
	// autowire service class

	@Autowired
	private CRMservice crmservice;

	@RequestMapping("/list")
	public String listCustomers(Model model) {
		List<Customers> customers = crmservice.getCustomers();
		model.addAttribute("customers", customers);
		return "customerlist";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Customers customer = new Customers();
		theModel.addAttribute("customer", customer);

		// send over to our form
		return "newform";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {

		// get the student from the service
		Customers crm = crmservice.getCustomer(theId);

		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("customer", crm);

		// send over to our form
		return "newform";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("f_name") String f_name,
			@RequestParam("l_name") String l_name, @RequestParam("email") String email) {

		System.out.println(id);
		Customers crm;
		if (id != 0) {
			crm = crmservice.getCustomer(id);
			crm.setF_name(f_name);
			crm.setL_name(l_name);
			crm.setEmail(email);
		} else
			crm = new Customers(f_name, l_name, email);
		// save the Student
		crmservice.save(crm);

		// use a redirect to prevent duplicate submissions
		return "redirect:/Customers/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

		// delete the student
		crmservice.deletebyId(theId);

		return "redirect:/Customers/list";

	}

}
