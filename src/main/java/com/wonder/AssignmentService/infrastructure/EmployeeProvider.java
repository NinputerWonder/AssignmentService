package com.wonder.AssignmentService.infrastructure;

import com.wonder.AssignmentService.domain.Employee;
import com.wonder.AssignmentService.domain.IEmployeeProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeProvider implements IEmployeeProvider {
    @Override
    public List<Employee> queryEmployees(long[] ids) {
        return null;
    }
}
