package com.example.autowire.name;

public class Car {

    private Specification specification;

    // autowire="byName" matches the property name "specification" with a bean id.
    public void setSpecification(Specification specification) {
        System.out.println("[byName] Spring called setSpecification() because bean id matches property name");
        this.specification = specification;
    }

    public void displayDetails() {

        System.out.println("[byName] Inside Car.displayDetails(), specification is ready");
        System.out.println("Car Details " + specification.toString());
    }
}
