package com.nationalize;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nationalize.dto.Comments;
import com.nationalize.dto.CountryDetailResponse;
import com.nationalize.dto.Department;
import com.nationalize.dto.Employee;
import com.nationalize.test.util.ConstantsUtil;

public class NationalizeAPITest {

	private static RestTemplate rt = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rt = new RestTemplate();
	}

	@Before
	public void setUp() throws Exception {
	}
	
	
	/**
	 * Invoke the rest end point  https://api.nationalize.io?name=michael
	 * and find the country with the highest probability of the response
	 * 
	 */
	@Test
	public void countryWithHighestProbabilityTest() {

		// Call the API and get the response
		ResponseEntity<CountryDetailResponse> response = rt.getForEntity(ConstantsUtil.API_URL, CountryDetailResponse.class);

		// check response status
		Assert.assertEquals(400, response.getStatusCodeValue(), 200);

		// get country details from response
		CountryDetailResponse countryResponse = response.getBody();

		// find the highest probability 
		double prob = countryResponse.getCountry().stream().mapToDouble(v -> v.getProbability()).max()
				.orElseThrow(NoSuchElementException::new);

		// assert the result
		Assert.assertEquals(0.01, prob, ConstantsUtil.EXPECTED_VALUE);

	}
	
	/**
	 * Get the Department which has the max number of Employees
	 * 
	 */
	@Test
	public void departmentWhichHasTheMaxNumberOfEmployeesTest() {

		// convert json string to java object
		List<Employee> employeeList = ConstantsUtil.convertStringToEmployeeObject();
		
		// loop through the employee object and find the Department which has the max number of Employees
		// and map the name department and max departs count
		Map<String, Long> emp = employeeList.stream().map(v-> v.getDepartment()).collect(
				Collectors.groupingBy(Department::getName, 
                TreeMap::new, Collectors.counting()));
		
		// print the details 
		emp.forEach((department, count) -> System.out.printf("%s has %d employee(s)%n", department, count));
		
		//get the Get the Department which has the max number of Employees
	    String maxDepartments = Collections.max(emp.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
	
	    //assert the result
	    Assert.assertEquals("", maxDepartments, ConstantsUtil.EXPECTED_DEPARTMENT);
		
	}
	
	
	/**
	 * Get the Oldest Employee from the Json below
	 * 
	 */
	@Test
	public void oldestEmployeeFromTest() {

		// convert json string to java object
		List<Employee> employeeList = ConstantsUtil.convertStringToEmployeeObject();
		
		// loop through the employee object and find the Department which has the max number of Employees
		// and map the name department and max departs count
		Map<String, Long> emp = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getStartDate, 
                TreeMap::new, Collectors.counting()));
		
		//get the Get the Department which has the max number of Employees
	    String age = Collections.max(emp.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
	
	    System.out.println("Oldest Employee Date of Birth :" + age);
	    
	    //assert the result
	    Assert.assertEquals("01-01-9999", age, "1923-09-03");
		
	}
	
	
	/**
	 * To count the unique characters in the body and the count of the same.
	 */
	@Test
	public void uniqueCharactersInTheBodyAndTheCount() {

		// Call the API and get the response
		List<String> bodyContent = getBodyContent();
		 
		Map<Character, Long> charFrequency = bodyContent.stream() //Stream<String>
		        .flatMap(a -> a.chars().mapToObj(c -> (char) c)) // Stream<Character>
		        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println("To count the unique characters in the body and the count of the same:");
		charFrequency.forEach((chars, count) -> {
			if(count == 1){
				System.out.println("Charecter is: "+chars +" count is:"+count);
			}
		});		
		
	}

	
	/**
	 * API call to get comment details and convert to java object
	 * and the return body of the string
	 * @return
	 */
	private List<String> getBodyContent() {
		ResponseEntity<String> response = rt.getForEntity(ConstantsUtil.COMMETNS_API_URL,String.class);
		// check response status
		Assert.assertEquals(400, response.getStatusCodeValue(), 200);
		
		// convert to comments object
		List<Comments> comments = null;
		try {
			comments = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<Comments>>() {
			});
		} catch (JsonMappingException e) {
			Assert.fail();
		} catch (JsonProcessingException e) {
			Assert.fail();
		}
	
		List<String> bodyContent = comments.stream().map(v->v.getBody()).distinct().collect(Collectors.toList());
		return bodyContent;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	

	@After
	public void tearDown() throws Exception {
	}

}
