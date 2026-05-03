package com.example.autowire.constructor;

public class Car {

    private Specification specification;

    // autowire="constructor" finds a Specification bean and passes it here.
    public Car(Specification specification) {
        System.out.println("[constructor] Spring called Car(Specification) while creating the Car bean");
        this.specification = specification;
    }

    public void displayDetails() {

        System.out.println("[constructor] Inside Car.displayDetails(), specification is ready");
        System.out.println("Car Details " + specification.toString());
    }
}
