package com.example.componentscan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Employee {


//    Value Annotation is used to assign values to the varibales.

    @Value("241241")
    private int EmployeeId;

    @Value("Sandeep")
    private String FirstName;

    @Value("Kumar")
    private String LastName;

    @Value("#{4*4}")
    private int Salary;

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getSalary() {
        return Salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId=" + EmployeeId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Salary=" + Salary +
                '}';
    }

    public void setSalary(int salary) {
        Salary = salary;
    }
}
