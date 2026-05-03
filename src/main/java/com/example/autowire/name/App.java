package com.example.autowire.name;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        System.out.println("[byName] Starting application");
        System.out.println("[byName] Loading Spring context from autowireByName.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("autowireByName.xml");

        System.out.println("[byName] Requesting bean with id MyCar from Spring container");
        Car myCar = (Car) context.getBean("MyCar");

        System.out.println("[byName] Calling displayDetails() on Car bean");
        myCar.displayDetails();

        System.out.println("[byName] Application finished");
    }

}
