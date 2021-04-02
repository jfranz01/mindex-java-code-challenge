package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG              = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) 
    {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        LOG.debug("Creating ReportingStructure with employee id [{}]", id);

        return createReportingStructure(employee);
    }

    private ReportingStructure createReportingStructure(Employee employee)
    {
    	int numberOfReports = getNumberOfReports(employee);
    	
    	return new ReportingStructure(employee, numberOfReports);
    }
    
    private int getNumberOfReports(Employee employee)
    {
    	if( employee.getDirectReports() == null )
    	{
        	return 0;
    	}
    	
    	int numberOfReports = employee.getDirectReports().size();

    	for(int i=0; i < employee.getDirectReports().size(); i++)
    	{

    		employee.getDirectReports().set(i, employeeRepository.findByEmployeeId(employee.getDirectReports().get(i).getEmployeeId()));

    		numberOfReports += getNumberOfReports(employee.getDirectReports().get(i)); 

    	}
    	
    	return numberOfReports;

    }

}



