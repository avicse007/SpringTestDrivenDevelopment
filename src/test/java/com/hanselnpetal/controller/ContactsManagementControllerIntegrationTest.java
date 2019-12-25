package com.hanselnpetal.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hanselnpetal.domain.CustomerContact;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContactsManagementControllerIntegrationTest {

	@Autowired
	private ContactsManagementController contactsManagementController;
	
	@Test
	public void testAddContactHappyPath() {
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("testFirstName");
		aContact.setLastName("testLastName");
		aContact.setEmail("testEmail@test.com");
		aContact.setDeliveryAddressCity("testCity");
		aContact.setDeliveryAddressLine1("testDeliveryAddressLine1");
		aContact.setDeliveryAddressLine2("testDeliveryAddressLine2");
		aContact.setDeliveryAddressLZipCode("testDeliveryAddressLZipCode");
		aContact.setDeliveryAddressState("testDeliveryAddressState");
		
		//Post our CustomerContact from bean to the controller 
		
		String outcome = contactsManagementController.processAddContactSubmit(aContact);
		
		//Assert that outcome is expected
		
		assertThat(outcome,is(equalTo("success")));
	}
	
	@Test
	public void testAddContactFirstNameMissing() {
		CustomerContact aContact = new CustomerContact();
		aContact.setLastName("testLastName");
		aContact.setEmail("testEmail@test.com");
		aContact.setDeliveryAddressCity("testCity");
		aContact.setDeliveryAddressLine1("testDeliveryAddressLine1");
		aContact.setDeliveryAddressLine2("testDeliveryAddressLine2");
		aContact.setDeliveryAddressLZipCode("testDeliveryAddressLZipCode");
		aContact.setDeliveryAddressState("testDeliveryAddressState");
		
		//Post our CustomerContact from bean to the controller 
		
		String outcome = contactsManagementController.processAddContactSubmit(aContact);
		
		//Assert that outcome is expected
		
		assertThat(outcome,is(equalTo("failure")));
	}

}
