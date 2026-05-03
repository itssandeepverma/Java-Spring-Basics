package com.example.autowired.annonation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Manager {

        @Autowired   //Field Injection
        @Qualifier("employee") // In big project, multiple bean with same type but different names
        private  Employee employee;

//        @Autowired   //Construction Injection
//        public Manager(Employee employee) {
//            this.employee = employee;
//        }

    @Override
    public String toString() {
        return "Manager{" +
                "employee=" + employee +
                '}';
    }
}
