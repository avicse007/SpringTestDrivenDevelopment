package com.hanselnpetal.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ContactsManagementControllerUnitTest {

	private final static ObjectMapper MAPPER = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContactsManagementService contactsManagementService;
	
	@InjectMocks
	private ContactsManagementController contactsManagementController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void testAddContactHappyPath() throws Exception {
		//setup mock contact required for mock service
		CustomerContact amockCustomerContact = new CustomerContact();
		amockCustomerContact.setFirstName("testfirstName");
		
		when(contactsManagementService.add(any(CustomerContact.class)))
		.thenReturn(amockCustomerContact);
		
		//simulate form bean that would post from the webpage
		
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("testfirstName");
		aContact.setEmail("test@email.com");
		
		//simulate the form submit
		mockMvc
		.perform(post("/addContact", aContact))
		.andExpect(status().isOk())
		.andReturn();
	} 
	
	@Test
	public void testAddContactBizServiceRuleNotSatisfied()throws Exception{
		
		//setup a mock response of NULL object returned from the mock service component
		when(contactsManagementService.add(any(CustomerContact.class)))
		.thenReturn(null);
		
		//simulate a form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName(null);
		
		//simulate the form submit (POST)
		mockMvc.perform(post("/addContact",aContact))
		.andDo(print())
		.andExpect(status().is(302)).andReturn();
	}
	
	@Test
	public void testAddContactOccasionHappyPath() throws Exception {
		// implement this
	}
}
