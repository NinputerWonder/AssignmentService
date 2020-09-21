package com.wonder.AssignmentService.infrastructure;

import com.wonder.AssignmentService.domain.Employee;
import com.wonder.AssignmentService.domain.IEmployeeProvider;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeProvider implements IEmployeeProvider {
    @Override
    public List<Employee> queryEmployees(long[] ids) {
        RestTemplate template = new RestTemplate();
        HttpEntity<long[]> request = new HttpEntity<>(ids);
        Employee[] forObject  = template.postForObject("http://localhost:8888/employees", request, Employee[].class);
        return Arrays.asList(forObject);
    }
}
