/**
 * 
 */
package com.nationalize.test.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nationalize.dto.Employee;

/**
 * @author Venkatesu Mandadi
 *
 */
public class ConstantsUtil {

	public final static String API_URL = "https://api.nationalize.io?name=michael";
	public final static String COMMETNS_API_URL = "https://jsonplaceholder.typicode.com/comments?q=Eliseo@gardner.biz";
	public final static double EXPECTED_VALUE= 0.08986482266532715;
	
	public final static String EXPECTED_DEPARTMENT= "IT";
	public final static String EXPECTED_AGE= "1923-09-03";
	
	public final static String EMPLOYEE_RESPONSE = "[{\"id\":1,\"name\":\"Hello One\",\"department\":{\"id\":1,\"name\":\"IT\",\"active\":true},\"startDate\":\"1977-09-03\",\"active\":true},{\"id\":2,\"name\":\"Hello Two\",\"department\":{\"id\":2,\"name\":\"Finance\",\"active\":true},\"startDate\":\"1923-09-03\",\"active\":true},{\"id\":3,\"name\":\"Hello Three\",\"department\":{\"id\":3,\"name\":\"HR\",\"active\":true},\"startDate\":\"1926-02-03\",\"active\":true},{\"id\":4,\"name\":\"Hello Four\",\"department\":{\"id\":1,\"name\":\"IT\",\"active\":true},\"startDate\":\"1955-12-03\",\"active\":true},{\"id\":5,\"name\":\"Hello Five\",\"department\":{\"id\":4,\"name\":\"Admin\",\"active\":true},\"startDate\":\"1938-06-03\",\"active\":false},{\"id\":6,\"name\":\"Hello Six\",\"department\":{\"id\":1,\"name\":\"IT\",\"active\":true},\"startDate\":\"1962-08-03\",\"active\":true}]";
	
	
	/**
	 * Convert java string to java object using object mapper 
	 * inorder to read employee object.
	 * 
	 * @return
	 */
	public static List<Employee> convertStringToEmployeeObject(){
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employee = null;
		try {
			employee = mapper.readValue(EMPLOYEE_RESPONSE, new TypeReference<List<Employee>>() {
			});
		} catch (JsonMappingException e) {
			return employee;
		} catch (JsonProcessingException e) {
			return employee;
		}
		return employee;
	}
	
	
}
