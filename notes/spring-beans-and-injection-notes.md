# Spring Beans and Injection Notes

## What Is a Spring Bean?

A Spring bean is a Java object that is created, configured, and managed by the Spring container.

In normal Java, we usually create objects ourselves:

```java
MyBean myBean = new MyBean();
myBean.setMessage("Hello");
```

In Spring, we can give this responsibility to Spring:

```xml
<bean id="myBean" class="car.example.MyBean">
    <property name="message" value="I am a first bean"/>
</bean>
```

This means:

- Create an object of `car.example.MyBean`
- Store it in the Spring container with the id `myBean`
- Set its `message` property to `I am a first bean`

The main idea is:

Spring controls object creation instead of the application code directly creating every object with `new`.

This is called Inversion of Control, or IoC.

## What Is the Spring Container?

The Spring container is the part of Spring that creates and manages beans.

In our code, the container is created here:

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("applicationBeanContext.xml");
```

`ApplicationContext` reads the XML file, finds the bean definitions, creates the objects, sets their values, and keeps them ready for use.

After that, we can ask Spring for a bean:

```java
MyBean myBean = (MyBean) context.getBean("myBean");
```

This means:

Get the bean whose id is `myBean` from the Spring container.

## Bean Example in This Project

The class is:

```java
package car.example;

public class MyBean {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMessage(){
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
```

This is a plain Java class. It does not extend a Spring class and it does not use Spring annotations.

Spring can still manage it because we define it in XML:

```xml
<bean id="myBean" class="car.example.MyBean">
    <property name="message" value="I am a first bean"/>
</bean>
```

Spring does this internally:

```java
MyBean myBean = new MyBean();
myBean.setMessage("I am a first bean");
```

Then in `App.java`, we load the XML:

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("applicationBeanContext.xml");
```

Then we fetch the bean:

```java
MyBean myBean = (MyBean) context.getBean("myBean");
```

Then we changed the message manually:

```java
myBean.setMessage("Hello Spring World");
```

So even though XML first sets:

```text
I am a first bean
```

the Java code later overwrites it with:

```text
Hello Spring World
```

That is why the output becomes:

```text
MyBean{message='Hello Spring World'}
```

## What Is Dependency Injection?

Dependency Injection means giving an object the things it needs from outside, instead of making the object create those things itself.

Example without dependency injection:

```java
public class Car {
    private Specification specification = new Specification();
}
```

Here, `Car` creates its own `Specification`. This makes `Car` tightly connected to that exact creation logic.

Example with dependency injection:

```java
public class Car {
    private Specification specification;

    public Car(Specification specification) {
        this.specification = specification;
    }
}
```

Here, `Car` receives `Specification` from outside. The object is injected into `Car`.

The main benefit:

- Classes become easier to change
- Classes become easier to test
- Object creation is handled in one place
- Classes focus on their real job instead of creating dependencies

## Main Types of Injection

The common dependency injection types are:

- Constructor injection
- Setter injection
- Field injection

In this project, we are mainly using XML-based constructor and setter/property injection.

## Constructor Injection

Constructor injection means dependencies are passed through the constructor.

In this project, constructor injection is shown with:

- `car.example.constructor.injection.Car`
- `car.example.constructor.injection.Specification`
- `applicationConstructorInjection.xml`

The `Specification` class contains car details:

```java
public class Specification {

    private String make;
    private String model;

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
```

The XML creates a `Specification` bean:

```xml
<bean id="CarSpecification" class="car.example.constructor.injection.Specification">
    <property name="make" value="Toyota"/>
    <property name="model" value="Corolla"/>
</bean>
```

This means Spring creates a `Specification` object and sets:

```text
make = Toyota
model = Corolla
```

Then the XML creates a `Car` bean:

```xml
<bean id="MyCar" class="car.example.constructor.injection.Car">
    <constructor-arg ref="CarSpecification"/>
</bean>
```

This means:

Create `Car` and pass the `CarSpecification` bean into its constructor.

So the `Car` class must have a matching constructor:

```java
public class Car {

    private Specification specification;

    public Car(Specification specification) {
        this.specification = specification;
    }
}
```

Spring internally does something similar to:

```java
Specification specification = new Specification();
specification.setMake("Toyota");
specification.setModel("Corolla");

Car myCar = new Car(specification);
```

Then in `App.java`:

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("applicationConstructorInjection.xml");

Car myCar = (Car) context.getBean("MyCar");
myCar.displayDetails();
```

The output is:

```text
Car Details Specification{make='Toyota', model='Corolla'}
```

### Why Constructor Injection Is Useful

Constructor injection is useful when a dependency is required.

For example, a `Car` should not exist without its `Specification`.

This constructor makes that rule clear:

```java
public Car(Specification specification) {
    this.specification = specification;
}
```

If Spring cannot provide a `Specification`, it cannot create the `Car`.

That is a good thing because it fails early and clearly.

### Error We Fixed

The XML had this:

```xml
<constructor-arg ref="CarSpecification"/>
```

That told Spring to call a constructor like this:

```java
Car(Specification specification)
```

But the `Car` class did not have that constructor. It only had a setter.

So Spring failed with:

```text
Could not resolve matching constructor
```

The fix was to add this constructor:

```java
public Car(Specification specification) {
    this.specification = specification;
}
```

## Setter Injection

Setter injection means dependencies or values are passed using setter methods after the object is created.

The basic pattern is:

```java
public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
```

And the XML is:

```xml
<bean id="myBean" class="car.example.MyBean">
    <property name="message" value="I am a first bean"/>
</bean>
```

Spring internally does:

```java
MyBean myBean = new MyBean();
myBean.setMessage("I am a first bean");
```

This is setter/property injection.

### Setter Injection With Simple Values

Use `value` when injecting simple values like:

- String
- int
- boolean
- double

Example:

```xml
<property name="message" value="I am a first bean"/>
```

This calls:

```java
setMessage("I am a first bean");
```

### Setter Injection With Another Bean

Use `ref` when injecting another bean.

Correct pattern:

```xml
<bean id="CarSpecification" class="car.example.setter.injection.Specification">
    <property name="make" value="Toyota"/>
    <property name="model" value="Corolla"/>
</bean>

<bean id="MyCar" class="car.example.setter.injection.Car">
    <property name="specification" ref="CarSpecification"/>
</bean>
```

This means:

- Create `CarSpecification`
- Create `MyCar`
- Call `setSpecification(CarSpecification)`

The Java class should look like:

```java
public class Car {

    private Specification specification;

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
}
```

Important:

Use `ref` for another bean:

```xml
<property name="specification" ref="CarSpecification"/>
```

Do not use `value` for another bean:

```xml
<property name="specification" value="CarSpecification"/>
```

`value` means plain text. `ref` means another Spring bean.

## Constructor Injection vs Setter Injection

Constructor injection:

- Dependency is provided while creating the object
- Best for required dependencies
- Object is complete immediately after construction
- Helps avoid missing required values

Setter injection:

- Dependency is provided after creating the object
- Best for optional values
- Object can be created first and configured later
- Requires setter methods

Example decision:

Use constructor injection when:

```text
A Car must have a Specification.
```

Use setter injection when:

```text
A value is optional or can be changed later.
```

## Field Injection

Field injection means Spring injects the dependency directly into the field.

It usually looks like this in annotation-based Spring:

```java
@Autowired
private Specification specification;
```

We have not used field injection in this XML-based code.

Field injection is simple, but constructor injection is usually preferred because:

- Required dependencies are clearer
- Testing is easier
- The class can be created without depending directly on Spring

## Important XML Keywords

`bean`:

Defines an object managed by Spring.

```xml
<bean id="myBean" class="car.example.MyBean"/>
```

`id`:

The name used to retrieve or reference the bean.

```xml
id="myBean"
```

`class`:

The full package and class name of the Java class Spring should create.

```xml
class="car.example.MyBean"
```

`property`:

Used for setter injection.

```xml
<property name="message" value="Hello"/>
```

`name`:

The property name. Spring maps it to a setter method.

```xml
name="message"
```

This maps to:

```java
setMessage(...)
```

`value`:

Used for simple values.

```xml
value="Toyota"
```

`ref`:

Used to refer to another Spring bean.

```xml
ref="CarSpecification"
```

`constructor-arg`:

Used for constructor injection.

```xml
<constructor-arg ref="CarSpecification"/>
```

## Full Flow of Our Constructor Injection Example

1. `App.java` starts.
2. `ClassPathXmlApplicationContext` loads `applicationConstructorInjection.xml`.
3. Spring reads the `CarSpecification` bean.
4. Spring creates a `Specification` object.
5. Spring sets `make` to `Toyota`.
6. Spring sets `model` to `Corolla`.
7. Spring reads the `MyCar` bean.
8. Spring sees `<constructor-arg ref="CarSpecification"/>`.
9. Spring finds the constructor `Car(Specification specification)`.
10. Spring creates `Car` by passing the `Specification` object.
11. `App.java` gets the `MyCar` bean from Spring.
12. `displayDetails()` prints the car details.

## Full Flow of Our Basic Bean Example

1. `App.java` starts.
2. `ClassPathXmlApplicationContext` loads `applicationBeanContext.xml`.
3. Spring reads the `myBean` bean definition.
4. Spring creates a `MyBean` object.
5. Spring calls `setMessage("I am a first bean")`.
6. `App.java` gets the bean using `context.getBean("myBean")`.
7. Java code calls `setMessage("Hello Spring World")`.
8. The new message replaces the old XML message.
9. `System.out.println(myBean)` prints the final object.

## Summary

A bean is an object managed by Spring.

Dependency injection means giving an object its required values or objects from outside.

In this project:

- `MyBean` shows a simple Spring bean
- `applicationBeanContext.xml` shows setter/property injection with a simple string value
- `Specification` is another Spring bean with simple values
- `Car` receives `Specification` through constructor injection
- `applicationConstructorInjection.xml` shows how one bean can be injected into another bean

The main thing we are trying to achieve:

Instead of manually creating and connecting objects everywhere, we define them in Spring configuration and let Spring create, configure, and connect them for us.

## Related Notes

For XML autowiring examples using `byName`, `byType`, and `constructor`, see:

```text
notes/spring-autowiring-notes.md
```
