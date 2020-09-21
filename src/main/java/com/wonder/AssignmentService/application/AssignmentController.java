package com.wonder.AssignmentService.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assignments")
public class AssignmentController {
    @Autowired
    AssignmentApplicationService assignmentApplicationService;

    @GetMapping("/{id}")
    public AssignmentDto get(@PathVariable("id") long id) {
        return assignmentApplicationService.findById(id);
    }
}
