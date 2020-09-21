package com.wonder.AssignmentService;

import com.wonder.AssignmentService.application.AssignmentApplicationService;
import com.wonder.AssignmentService.application.AssignmentDto;
import com.wonder.AssignmentService.domain.Assignment;
import com.wonder.AssignmentService.domain.Employee;
import com.wonder.AssignmentService.domain.IAssignmentRepository;
import com.wonder.AssignmentService.domain.IEmployeeProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssignmentApplicationServiceTests {

	@Autowired
	AssignmentApplicationService assignmentApplicationService;

	@MockBean
	IEmployeeProvider employeeProvider;

	@MockBean
	IAssignmentRepository assignmentRepository;

	@Test
	void should_get_assignment_by_id_with_employee_information() {

		when(employeeProvider.queryEmployees(new long[]{101, 102})).thenReturn(
				Arrays.asList(
						new Employee(){{id = 101; name = "Donald Duck"; age = 55;}},
						new Employee(){{id = 102; name = "Mickey Mouse"; age = 60;}}
				)
		);

		when(assignmentRepository.findById(10086)).thenReturn(new Assignment("ChongQing", 101, 102){{setId(10086);}});

		AssignmentDto assignment = assignmentApplicationService.findById(10086);

		assertEquals(10086, assignment.id);
		assertEquals("ChongQing", assignment.city);
		assertEquals(2, assignment.employees.size());

		Employee employee = assignment.employees.stream().filter(e -> e.id == 101).findFirst().orElse(null);
		assertEquals("Donald Duck", employee.name);
		assertEquals(55, employee.age);

		employee = assignment.employees.stream().filter(e -> e.id == 102).findFirst().orElse(null);
		assertEquals("Mickey Mouse", employee.name);
		assertEquals(60, employee.age);
	}

}
