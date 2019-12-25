package com.hanselnpetal.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hanselnpetal.domain.CustomerContact;

//TO instruct Junit that we are running Spring test
//@RunWith(SpringRunner.class)

//To instuct Junit that we are running with MockitoJUnit Runner
@RunWith(MockitoJUnitRunner.class)

//To instruct junit not to load any controller component
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
/*
 * This will be for integrating testing of service as we are creating actual object for repository
 * and injecting in our service. 
 * 
 * 
 */


public class ContactsManagementServiceIntegrationTest {
	
	@Autowired
	private ContactsManagementService contactsManagementService;
	
	
	@Test
	public void testAddContactHappyPath() {
		
		// Create a contact
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("testFirstName");
		aContact.setLastName("testLastName");
		aContact.setEmail("testEmail@test.com");
		aContact.setDeliveryAddressCity("testCity");
		aContact.setDeliveryAddressLine1("testDeliveryAddressLine1");
		aContact.setDeliveryAddressLine2("testDeliveryAddressLine2");
		aContact.setDeliveryAddressLZipCode("testDeliveryAddressLZipCode");
		aContact.setDeliveryAddressState("testDeliveryAddressState");
		
		// Test adding the contact
		
		CustomerContact newCustomerContact = contactsManagementService.add(aContact);
		
		// Verify the addition
		assertNotNull(newCustomerContact);
		assertNotNull(newCustomerContact.getId());
		assertEquals("testFirstName", newCustomerContact.getFirstName());
		assertEquals("testLastName", newCustomerContact.getLastName());
		assertEquals("testEmail@test.com", newCustomerContact.getEmail());
		assertEquals("testCity", newCustomerContact.getDeliveryAddressCity());
		assertEquals("testDeliveryAddressLine1", newCustomerContact.getDeliveryAddressLine1());
		assertEquals("testDeliveryAddressLine2", newCustomerContact.getDeliveryAddressLine2());
		assertEquals("testDeliveryAddressLZipCode", newCustomerContact.getDeliveryAddressLZipCode());
		assertEquals("testDeliveryAddressState", newCustomerContact.getDeliveryAddressState());
		
		
	}
}
