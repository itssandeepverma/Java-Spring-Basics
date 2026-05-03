package com.example.autowire.type;

public class Car {

    private Specification specification;

    // autowire="byType" finds a bean of type Specification and calls this setter.
    public void setSpecification(Specification specification) {
        System.out.println("[byType] Spring called setSpecification() because it found one Specification bean type");
        this.specification = specification;
    }

    public void displayDetails() {

        System.out.println("[byType] Inside Car.displayDetails(), specification is ready");
        System.out.println("Car Details " + specification.toString());
    }
}
