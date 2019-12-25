TDD
=======

 ## Introduction to JUnit

JUnit is the most popular Java Unit testing framework

We typically work in large projects - some of these projects have more than 2000 source files or sometimes it might be as big as 10000 files with one million lines of code.

Before unit testing, we depend on deploying the entire app and checking if the screens look great. But that’s not very efficient. And it is manual.

Unit Testing focuses on writing automated tests for individual classes and methods.

JUnit is a framework which will help you call a method and check (or assert) whether the output is as expected.

The important thing about automation testing is that these tests can be run with continuous integration - as soon as some code changes.

 #### Example Source Code to Test

package com.in28minutes.junit;

public class MyMath {
	int sum(int[] numbers) {
		int sum = 0;
		for (int i : numbers) {
			sum += i;
		}
		return sum;
	}
}
 ### Unit test for the sum method

package com.in28minutes.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMathTest {
	MyMath myMath = new MyMath();

	// MyMath.sum
	// 1,2,3 => 6
	@Test
	public void sum_with3numbers() {
		System.out.println("Test1");
		assertEquals(6, myMath.sum(new int[] { 1, 2, 3 }));
	}

	@Test
	public void sum_with1number() {
		System.out.println("Test2");
		assertEquals(3, myMath.sum(new int[] { 3 }));
	}
}

 ### Other Important JUnit annotations

 #### @Before @After annotations
 Run before and after every test method in the class
#### @BeforeClass @AfterClass annotations
Static methods which are executed once before and after a test class
package com.in28minutes.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMathTest {
	MyMath myMath = new MyMath();

	@Before
	public void before() {
		System.out.println("Before");
	}

	@After
	public void after() {
		System.out.println("After");
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After Class");
	}

	// MyMath.sum
	// 1,2,3 => 6
	@Test
	public void sum_with3numbers() {
		System.out.println("Test1");
		assertEquals(6, myMath.sum(new int[] { 1, 2, 3 }));
	}

	@Test
	public void sum_with1number() {
		System.out.println("Test2");
		assertEquals(3, myMath.sum(new int[] { 3 }));
	}
}
## Introduction to Mockito

Mockito is the most popular mocking framework in Java.

In the example below SomeBusinessImpl depends on DataService. When we write a unit test for SomeBusinessImpl, we will want to use a mock DataService - one which does not connect to a database.

package com.in28minutes.mockito.mockitodemo;

public class SomeBusinessImpl {
	private DataService dataService;

	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

Writing a test with Mockito
package com.in28minutes.mockito.mockitodemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class SomeBusinessMockTest {

	@Test
	public void testFindTheGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}

	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(15, result);
	}

}
## Notes

### DataService dataServiceMock = mock(DataService.class) - We are using the mock method to create a mock.
### when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 }) - stubbing the mock to return specific data

## Using Mockito Annotations - @Mock, @InjectMocks, @RunWith(MockitoJUnitRunner.class)
package com.in28minutes.mockito.mockitodemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockAnnotationsTest {

	@Mock
	DataService dataServiceMock;

	@InjectMocks
	SomeBusinessImpl businessImpl;

	@Test
	public void testFindTheGreatestFromAllData() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		assertEquals(24, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		assertEquals(15, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_NoValues() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
	}
}

### Notes

#### 1. @Mock DataService dataServiceMock; - Create a mock for DataService.
#### 2. @InjectMocks SomeBusinessImpl businessImpl; - Inject the mocks as dependencies into businessImpl.
#### 3. @RunWith(MockitoJUnitRunner.class) - The JUnit Runner which causes all the initialization magic with @Mock and @InjectMocks to happen before the tests are run.

## Unit Testing with Mockito using MockitoRunner
Code below shows a unit test with Mockito using MockitoJUnitRunner.

/src/test/java/com/in28minutes/springboot/tutorial/basics/example/unittest/BusinessServicesMockTest.java

@RunWith(MockitoJUnitRunner.class)
public class BusinessServicesMockTest {

	@Mock
	DataService dataServiceMock;

	@InjectMocks
	BusinessService businessImpl;

	@Test
	public void testFindTheGreatestFromAllData() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		assertEquals(24, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		assertEquals(15, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_NoValues() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
	}
}
### Notes

#### 1. @RunWith(MockitoJUnitRunner.class) public class BusinessServicesMockTest - The JUnit Runner which causes all the initialization magic with @Mock and @InjectMocks to happen before the tests are run.
#### 2. @Mock DataService dataServiceMock - Create a mock for DataService
#### 3. @InjectMocks BusinessService businessImpl - Inject the mocks as dependencies into BusinessService
There are three test methods testing three different scenarios - multiple values, one value and no value passed in.

## Unit Test launching the complete Spring Context using @MockBean
Example code below shows how we can write the same unit test launching up the complete Spring context.

/src/test/java/com/in28minutes/springboot/tutorial/basics/example/unittest/BusinessServicesMockSpringContextTest.java


@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServicesMockSpringContextTest {

	@MockBean
	DataService dataServiceMock;

	@Autowired
	BusinessService businessImpl;

	@Test
	public void testFindTheGreatestFromAllData() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		assertEquals(24, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		assertEquals(15, businessImpl.findTheGreatestFromAllData());
	}

	@Test
	public void testFindTheGreatestFromAllData_NoValues() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
	}
}
### Notes

#### 1. @RunWith(SpringRunner.class) - Spring Runner is used to launch up a spring context in unit tests.
#### 1. @SpringBootTest - This annotation indicates that the context under test is a @SpringBootApplication. The complete SpringBootTutorialBasicsApplication is launched up during the unit test.
#### 1. @MockBean DataService dataServiceMock - @MockBean annotation creates a mock for DataService. This mock is used in the Spring Context instead of the real DataService.
#### 1. @Autowired BusinessService businessImpl - Pick the Business Service from the Spring Context and autowire it in.

## Choosing between the approaches
Launching the entire spring context makes the unit test slower. Unit tests will also start failing if there are errors in other beans in the contexts. So, the MockitoJUnitRunner approach is preferred.



