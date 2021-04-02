package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest 
{

    private               String       compensationUrl;
    private               String       compensationIdUrl;

	private final static  Employee     employeePaulMcCartney     = new Employee("b7839309-3348-463b-a7e3-5de1c168reb3", "Paul", "McCartney", "Developer I", "Engineering", null);
	private final static  Compensation compensationPaulMcCartney = new Compensation(employeePaulMcCartney, 23489.34, "2021/03/31 12:32:24");


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() 
    {
    	compensationUrl   = "http://localhost:" + port + "/compensation";
    	compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCompensationCreateRead() 
    {

    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);    	
    	
        HttpEntity<Compensation> httpEntityRequest = new HttpEntity<>(compensationPaulMcCartney, headers);    	
    	
        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, httpEntityRequest, Compensation.class).getBody();

        assertNotNull(createdCompensation);
        assertEquals(employeePaulMcCartney.getEmployeeId(),         createdCompensation.getEmployee().getEmployeeId());
        assertEquals(employeePaulMcCartney.getFirstName(),          createdCompensation.getEmployee().getFirstName());
        assertEquals(employeePaulMcCartney.getLastName(),           createdCompensation.getEmployee().getLastName());
        assertEquals(employeePaulMcCartney.getPosition(),           createdCompensation.getEmployee().getPosition());
        assertEquals(employeePaulMcCartney.getDepartment(),         createdCompensation.getEmployee().getDepartment());
        assertEquals(compensationPaulMcCartney.getSalary(),         createdCompensation.getSalary(),   2);
        assertEquals(compensationPaulMcCartney.getEffectiveDate(),  createdCompensation.getEffectiveDate());


        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, employeePaulMcCartney.getEmployeeId()).getBody();

        assertNotNull(readCompensation);
        assertEquals(employeePaulMcCartney.getEmployeeId(),         readCompensation.getEmployee().getEmployeeId());
        assertEquals(employeePaulMcCartney.getFirstName(),          readCompensation.getEmployee().getFirstName());
        assertEquals(employeePaulMcCartney.getLastName(),           readCompensation.getEmployee().getLastName());
        assertEquals(employeePaulMcCartney.getPosition(),           readCompensation.getEmployee().getPosition());
        assertEquals(employeePaulMcCartney.getDepartment(),         readCompensation.getEmployee().getDepartment());
        assertEquals(compensationPaulMcCartney.getSalary(),         readCompensation.getSalary(),   2);
        assertEquals(compensationPaulMcCartney.getEffectiveDate(),  readCompensation.getEffectiveDate());
    
    }

 }
