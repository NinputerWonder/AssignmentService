package com.wonder.AssignmentService.domain;

public class Assignment {
    private String city;
    private long[] employeeIds;
    private long id;

    public Assignment(String city, long... employeeIds){

        this.city = city;
        this.employeeIds = employeeIds;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public long[] getEmployeeIds() {
        return employeeIds;
    }
}
