package com.wonder.AssignmentService.domain;

import java.util.List;

public interface IEmployeeProvider {
    List<Employee> queryEmployees(long[] ids);
}
