package com.example.autowire.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        System.out.println("[constructor] Starting application");
        System.out.println("[constructor] Loading Spring context from autowireByConstructor.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("autowireByConstructor.xml");

        System.out.println("[constructor] Requesting bean with id MyCar from Spring container");
        Car myCar = (Car) context.getBean("MyCar");

        System.out.println("[constructor] Calling displayDetails() on Car bean");
        myCar.displayDetails();

        System.out.println("[constructor] Application finished");
    }

}
