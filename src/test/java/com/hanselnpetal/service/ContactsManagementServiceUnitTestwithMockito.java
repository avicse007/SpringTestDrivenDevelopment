package com.hanselnpetal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.CustomerContact;

/*
 * This will be for unit testing of service as we are creating mock for repository
 * and injecting in our service. 
 * 
 */

	


@SuppressWarnings("deprecation")
//Run with Mockito
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactsManagementServiceUnitTestwithMockito {
    //Creates a mock
	@Mock
	private CustomerContactRepository customerContactRepository;
	
	//creates objects and inject mocked dependencies
	@InjectMocks
	private ContactsManagementService contactsManagementService;
	
	//
	@Before
    public void setup() {
		//we can enable Mockito annotations 
		//programmatically as well, by invoking 
		//MockitoAnnotations.initMocks():
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddContactHappyPath() {
		
		// Create a contact
		CustomerContact aMockContact = new CustomerContact();
		aMockContact.setFirstName("Jenny");
		aMockContact.setLastName("Johnson");
		
		when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(aMockContact);
				
		// Save the contact
		CustomerContact newContact = contactsManagementService.add(aMockContact);
		
		
		// Verify the save
		assertEquals("Jenny", newContact.getFirstName());
	}
	
}
