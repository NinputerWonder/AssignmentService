package com.wonder.AssignmentService.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {
    @Autowired
    AssignmentApplicationService assignmentApplicationService;

    public AssignmentDto get(long id) {
        return assignmentApplicationService.findById(id);
    }
}
