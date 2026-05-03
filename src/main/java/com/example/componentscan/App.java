package com.example.componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("componentScan.xml");
        Employee employee = (Employee) applicationContext.getBean("employee");
        System.out.println(employee.toString());

    }
}
