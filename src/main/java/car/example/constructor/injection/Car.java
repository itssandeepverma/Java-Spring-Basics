package car.example.constructor.injection;

public class Car {


    private Specification specification;

    public Car(Specification specification) {
        this.specification = specification;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails() {
        System.out.println("Car Details " + specification.toString());
    }
}
