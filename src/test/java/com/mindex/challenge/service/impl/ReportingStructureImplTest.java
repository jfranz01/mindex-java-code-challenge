package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest 
{

    private String employeeUrl;
    private String employeeIdUrl;
    private String reportingStructureIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() 
    {
        employeeUrl             = "http://localhost:" + port + "/employee";
        employeeIdUrl           = "http://localhost:" + port + "/employee/{id}";
    	reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
    }


    @Test
    public void testGetReportingStructureEmployeePaulMcCartney() 
    {

        String paulMcCartneyEmployeeId = "b7839309-3348-463b-a7e3-5de1c168beb3";
    	
        // Read checks
        ReportingStructure paulMcCartneyReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, paulMcCartneyEmployeeId).getBody();

        assertNotNull(paulMcCartneyReportingStructure.getEmployee().getEmployeeId());

        assertReportingStructurePaulMcCartney(paulMcCartneyReportingStructure);
        
    }


    @Test
    public void testGetReportingStructureEmployeeRingoStarr() 
    {

        String ringoStarrEmployeeId = "03aa1462-ffa9-4978-901b-7c001562cf6f";
    	
        // Read checks
        ReportingStructure ringoStarrReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, ringoStarrEmployeeId).getBody();

        assertNotNull(ringoStarrReportingStructure.getEmployee().getEmployeeId());

        assertReportingStructureRingoStarr(ringoStarrReportingStructure);
        
    }

    @Test
    public void testGetReportingStructureEmployeeFredFlinstone() 
    {

        String fredFlinstoreEmployeeId = "caaaaaad-1bbd-4cc3-8dd8-6eeeeeeeeeec";
    	
        // Read checks
        ReportingStructure fredFlinstorneReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, fredFlinstoreEmployeeId).getBody();

        assertNotNull(fredFlinstorneReportingStructure.getEmployee().getEmployeeId());

        assertReportingStructureFredFlinstone(fredFlinstorneReportingStructure);
        
    }

    @Test
    public void testMultiReadsInARow()
    {
        String paulMcCartneyEmployeeId = "b7839309-3348-463b-a7e3-5de1c168beb3";
    	
        // Read checks
        ReportingStructure paulMcCartneyReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, paulMcCartneyEmployeeId).getBody();

        assertNotNull(paulMcCartneyReportingStructure.getEmployee().getEmployeeId());

        assertReportingStructurePaulMcCartney(paulMcCartneyReportingStructure);
        


        String ringoStarrEmployeeId = "03aa1462-ffa9-4978-901b-7c001562cf6f";
    	
        // Read checks
        ReportingStructure ringoStarrReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, ringoStarrEmployeeId).getBody();

        assertNotNull(ringoStarrReportingStructure.getEmployee().getEmployeeId());

        assertReportingStructureRingoStarr(ringoStarrReportingStructure);
        


        String fredFlinstoreEmployeeId = "caaaaaad-1bbd-4cc3-8dd8-6eeeeeeeeeec";
    	
        // Read checks
        ReportingStructure fredFlinstorneReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, fredFlinstoreEmployeeId).getBody();

        assertNotNull(fredFlinstorneReportingStructure.getEmployee().getEmployeeId());

        assertReportingStructureFredFlinstone(fredFlinstorneReportingStructure);
        
    }
    
    
    private static void assertReportingStructurePaulMcCartney(ReportingStructure reportingStructure) 
    {

    	assertEquals(0, reportingStructure.getNumberOfReports());

    	assertEquals("b7839309-3348-463b-a7e3-5de1c168beb3", reportingStructure.getEmployee().getEmployeeId());
    	assertEquals("Paul",                                 reportingStructure.getEmployee().getFirstName());
    	assertEquals("McCartney",                            reportingStructure.getEmployee().getLastName());
    	assertEquals("Developer I",                          reportingStructure.getEmployee().getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports());

    }    


    
    private static void assertReportingStructureRingoStarr(ReportingStructure reportingStructure) 
    {

    	assertEquals(2, reportingStructure.getNumberOfReports());

    	assertEquals("03aa1462-ffa9-4978-901b-7c001562cf6f", reportingStructure.getEmployee().getEmployeeId());
    	assertEquals("Ringo",                                reportingStructure.getEmployee().getFirstName());
    	assertEquals("Starr",                                reportingStructure.getEmployee().getLastName());
    	assertEquals("Developer V",                          reportingStructure.getEmployee().getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDepartment());
    	assertNotNull(reportingStructure.getEmployee().getDirectReports());

    	assertEquals("62c1084e-6e34-4630-93fd-9153afb65309", reportingStructure.getEmployee().getDirectReports().get(0).getEmployeeId());
    	assertEquals("Pete",                                 reportingStructure.getEmployee().getDirectReports().get(0).getFirstName());
    	assertEquals("Best",                                 reportingStructure.getEmployee().getDirectReports().get(0).getLastName());
    	assertEquals("Developer II",                         reportingStructure.getEmployee().getDirectReports().get(0).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(0).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports());
    	
    	assertEquals("c0c2293d-16bd-4603-8e08-638a9d18b22c", reportingStructure.getEmployee().getDirectReports().get(1).getEmployeeId());
    	assertEquals("George",                               reportingStructure.getEmployee().getDirectReports().get(1).getFirstName());
    	assertEquals("Harrison",                             reportingStructure.getEmployee().getDirectReports().get(1).getLastName());
    	assertEquals("Developer III",                        reportingStructure.getEmployee().getDirectReports().get(1).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(1).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(1).getDirectReports());
    	
    }
    
    
    
    private static void assertReportingStructureFredFlinstone(ReportingStructure reportingStructure) 
    {

    	assertEquals(7, reportingStructure.getNumberOfReports());

    	assertEquals("caaaaaad-1bbd-4cc3-8dd8-6eeeeeeeeeec", reportingStructure.getEmployee().getEmployeeId());
    	assertEquals("Fred",                                 reportingStructure.getEmployee().getFirstName());
    	assertEquals("Flinstone",                            reportingStructure.getEmployee().getLastName());
    	assertEquals("Senior Software Developer",            reportingStructure.getEmployee().getPosition());
    	assertEquals("Information Technology",               reportingStructure.getEmployee().getDepartment());
    	assertNotNull(reportingStructure.getEmployee().getDirectReports());

    	assertEquals("16a596ae-edd3-4847-99fe-c4518e82c86f", reportingStructure.getEmployee().getDirectReports().get(0).getEmployeeId());
    	assertEquals("John",                                 reportingStructure.getEmployee().getDirectReports().get(0).getFirstName());
    	assertEquals("Lennon",                               reportingStructure.getEmployee().getDirectReports().get(0).getLastName());
    	assertEquals("Development Manager",                  reportingStructure.getEmployee().getDirectReports().get(0).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(0).getDepartment());
    	assertNotNull(reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports());

    	assertEquals("b7839309-3348-463b-a7e3-5de1c168beb3", reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(0).getEmployeeId());
    	assertEquals("Paul",                                 reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(0).getFirstName()); 
    	assertEquals("McCartney",                            reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(0).getLastName());
    	assertEquals("Developer I",                          reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(0).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(0).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(0).getDirectReports());
    	
    	assertEquals("03aa1462-ffa9-4978-901b-7c001562cf6f", reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getEmployeeId());
    	assertEquals("Ringo",                                reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getFirstName());
    	assertEquals("Starr",                                reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getLastName());
    	assertEquals("Developer V",                          reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDepartment());
    	assertNotNull(reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports());

    	assertEquals("62c1084e-6e34-4630-93fd-9153afb65309", reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(0).getEmployeeId());
    	assertEquals("Pete",                                 reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(0).getFirstName());
    	assertEquals("Best",                                 reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(0).getLastName());
    	assertEquals("Developer II",                         reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(0).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(0).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(0).getDirectReports());
    	
    	assertEquals("c0c2293d-16bd-4603-8e08-638a9d18b22c", reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(1).getEmployeeId());
    	assertEquals("George",                               reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(1).getFirstName());
    	assertEquals("Harrison",                             reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(1).getLastName());
    	assertEquals("Developer III",                        reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(1).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(1).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(0).getDirectReports().get(1).getDirectReports().get(1).getDirectReports());
    	
    	assertEquals("62c1084e-6e34-4630-93fd-9153afxxx309", reportingStructure.getEmployee().getDirectReports().get(1).getEmployeeId());
    	assertEquals("Kyle",                                 reportingStructure.getEmployee().getDirectReports().get(1).getFirstName());
    	assertEquals("Adams",                                reportingStructure.getEmployee().getDirectReports().get(1).getLastName());
    	assertEquals("Developer II",                         reportingStructure.getEmployee().getDirectReports().get(1).getPosition());
    	assertEquals("Information Technology",               reportingStructure.getEmployee().getDirectReports().get(1).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(1).getDirectReports());

    	assertEquals("c0vvv93d-16bd-4603-8e08-638a9d18b22c", reportingStructure.getEmployee().getDirectReports().get(2).getEmployeeId());
    	assertEquals("Jarred",                               reportingStructure.getEmployee().getDirectReports().get(2).getFirstName());
    	assertEquals("Law",                                  reportingStructure.getEmployee().getDirectReports().get(2).getLastName());
    	assertEquals("Applications Developer III",           reportingStructure.getEmployee().getDirectReports().get(2).getPosition());
    	assertEquals("Engineering",                          reportingStructure.getEmployee().getDirectReports().get(2).getDepartment());
    	assertNull(reportingStructure.getEmployee().getDirectReports().get(2).getDirectReports());

    }
    
}
