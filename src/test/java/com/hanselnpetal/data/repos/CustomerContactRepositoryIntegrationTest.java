package com.hanselnpetal.data.repos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hibernate.dialect.unique.DB2UniqueDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.hanselnpetal.domain.CustomerContact;

//Run with Spring runner
@RunWith(SpringRunner.class)
/*For testing Jpa based repository it contains other annotations like
 *@AutoConfigureDataJpa => imports all configuration needed for JPA testing
 *@AutoConfigureTestDatabase => use embedded database or specify another database external or staging
 *@AutoConfigureTestEntityManger=>Allowed us to have direct access to the EntityManager 
 *@Trnasational => Allowed to have roll back behavior after test execution  
 */
@DataJpaTest

@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CustomerContactRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	@Test
    public void testFindByEmail() {
		
		// setup data scenario
		CustomerContact aNewContact = new CustomerContact();
		aNewContact.setEmail("fredj@myemail.com");
        
		// save test data
		entityManager.persist(aNewContact);

        // Find an inserted record
        CustomerContact foundContact = customerContactRepository.findByEmail("fredj@myemail.com");
        
        assertThat(foundContact.getEmail(), is(equalTo("fredj@myemail.com")));
    }
	
}
