package com.wonder.AssignmentService;

import com.wonder.AssignmentService.application.AssignmentApplicationService;
import com.wonder.AssignmentService.application.AssignmentController;
import com.wonder.AssignmentService.application.AssignmentDto;
import com.wonder.AssignmentService.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AssignmentControllerTest {

    @Autowired
    AssignmentController assignmentController;

    @MockBean
    AssignmentApplicationService assignmentApplicationService;

    @Test
    public void should_call_assignment_application_service_when_get_by_id(){
        when(assignmentApplicationService.findById(10086)).thenReturn(new AssignmentDto(){
            {
                id = 10086;
                city = "Chongqing";
                employees = Arrays.asList(
                        new Employee(){{id = 1; name = "Donald Duck"; age=55;}},
                        new Employee(){{id = 2; name = "Mickey Mouse"; age=60;}}
                );
            }
        });
        AssignmentDto assignment =  assignmentController.get(10086);
        assertEquals(10086, assignment.id);
        assertEquals(2, assignment.employees.size());
        verify(assignmentApplicationService, times(1)).findById(10086);

    }
}
