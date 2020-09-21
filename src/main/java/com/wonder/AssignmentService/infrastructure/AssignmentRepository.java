package com.wonder.AssignmentService.infrastructure;

import com.wonder.AssignmentService.domain.Assignment;
import com.wonder.AssignmentService.domain.IAssignmentRepository;
import org.springframework.stereotype.Component;

@Component
public class AssignmentRepository implements IAssignmentRepository {
    @Override
    public Assignment findById(long id) {
        return new Assignment("San Jose", 1231343, 4321234){{setId(id);}};
    }
}
