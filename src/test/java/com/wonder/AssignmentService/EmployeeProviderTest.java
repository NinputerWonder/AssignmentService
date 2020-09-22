package com.wonder.AssignmentService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wonder.AssignmentService.domain.Employee;
import com.wonder.AssignmentService.domain.IEmployeeProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeProviderTest {

    @Autowired
    IEmployeeProvider employeeProvider;

    @Rule
    public WireMockRule rule = new WireMockRule(8888);

    @Test
    public void should_send_request_to_employee_service_to_get_employees() throws JsonProcessingException {

        final String responseBody = toJson(new Employee[]{
                new Employee() {{
                    id = 333;
                    name = "Donald Duck";
                    age = 55;
                }} ,
                new Employee() {{
                    id = 444;
                    name = "Mickey Mouse";
                    age = 60;
                }}
        });
        final String requestBody = toJson(new long[]{ 333 , 444 });

        stubFor(post(urlEqualTo("/employees"))
                .withHeader("Content-Type" , equalTo("application/json"))
                .withRequestBody(equalTo(requestBody))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type" , "application/json")
                        .withBody(responseBody))
        );

        final List<Employee> employees = employeeProvider.queryEmployees(new long[]{ 333 , 444 });

        assertEquals(2 , employees.size());

        Employee employee = employees.stream().filter(e -> e.id == 333).findFirst().orElse(null);
        assertEquals("Donald Duck" , employee.name);
        assertEquals(55 , employee.age);

        employee = employees.stream().filter(e -> e.id == 444).findFirst().orElse(null);
        assertEquals("Mickey Mouse" , employee.name);
        assertEquals(60 , employee.age);

        verify(1, postRequestedFor(urlEqualTo("/employees")));
    }

    String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
