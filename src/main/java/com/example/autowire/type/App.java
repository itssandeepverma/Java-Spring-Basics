package com.example.autowire.type;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        System.out.println("[byType] Starting application");
        System.out.println("[byType] Loading Spring context from autowireByType.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("autowireByType.xml");

        System.out.println("[byType] Requesting bean with id MyCar from Spring container");
        Car myCar = (Car) context.getBean("MyCar");

        System.out.println("[byType] Calling displayDetails() on Car bean");
        myCar.displayDetails();

        System.out.println("[byType] Application finished");
    }

}
