package com.wonder.AssignmentService.application;

import com.wonder.AssignmentService.domain.Assignment;
import com.wonder.AssignmentService.domain.Employee;
import com.wonder.AssignmentService.domain.IAssignmentRepository;
import com.wonder.AssignmentService.domain.IEmployeeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentApplicationService {

    @Autowired
    IAssignmentRepository assignmentRepository;

    @Autowired
    IEmployeeProvider employeeProvider;

    public AssignmentDto findById(long id) {
        Assignment assignment = assignmentRepository.findById(id);

        List<Employee> employeeList = employeeProvider.queryEmployees(assignment.getEmployeeIds());

        return new AssignmentDto(){{
            id = assignment.getId();
            city = assignment.getCity();
            employees = employeeList;
        }};
    }
}
