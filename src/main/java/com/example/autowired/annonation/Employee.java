package com.example.autowired.annonation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("employee")  // name can be given anything or it will make the class into smallcase and take the value
public class Employee {


//    Value Annotation is used to assign values to the varibales. 

    @Value("241241")
    private int EmployeeId;

    @Value("Lucky")
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
        return "[Using Annotations instead of XML] Employee{" +
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
