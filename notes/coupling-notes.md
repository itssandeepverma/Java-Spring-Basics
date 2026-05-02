# Coupling Notes

## Tight Coupling

Tight coupling means one class depends directly on a concrete class. This makes the code harder to change, test, or extend because the dependent class is responsible for creating and using a specific implementation.

In this project, tight coupling is shown in `com.tight.coupling.UserManager`.

```java
private UserDatabase userDatabase = new UserDatabase();
```

Why this is tightly coupled:

- `UserManager` is directly tied to `UserDatabase`.
- If data should come from somewhere else, `UserManager` must be changed.
- Testing is harder because a fake or alternate provider cannot be injected easily.

How it works in this code:

- `TIghtCouplingExample` creates `UserManager`.
- `UserManager` internally creates `UserDatabase`.
- `getUserInfo()` always calls `UserDatabase#getUserDetails()`.

So the dependency is fixed inside the class.

## Loose Coupling

Loose coupling means a class depends on an abstraction, not a concrete implementation. This makes the code easier to replace, extend, and test.

In this project, loose coupling is shown with the `UserDataProvider` interface.

```java
public interface UserDataProvider {
    String getUserDetails();
}
```

Two classes implement that interface:

- `UserDatabaseProvider`
- `WebDatabaseProvider`

`com.loose.coupling.UserManager` depends on the interface:

```java
private UserDataProvider userDataProvider;

public UserManager(UserDataProvider userDataProvider) {
    this.userDataProvider = userDataProvider;
}
```

Why this is loosely coupled:

- `UserManager` does not know whether data comes from a database or web source.
- Any class implementing `UserDataProvider` can be passed in.
- The behavior can change without modifying `UserManager`.

How it is achieved in this code:

1. An interface (`UserDataProvider`) defines the contract.
2. Multiple classes implement the same contract.
3. `UserManager` receives the dependency through its constructor.
4. `LooseCouplingExample` decides which implementation to pass.

Example from this project:

```java
UserDataProvider databaseProvider = new UserDatabaseProvider();
UserManager userManager = new UserManager(databaseProvider);

UserDataProvider webDatabaseProvider = new WebDatabaseProvider();
UserManager webDatabaseManager = new UserManager(webDatabaseProvider);
```

This is constructor injection. It is the reason the class is loosely coupled.

## Summary

Tight coupling in this project:

- `UserManager` directly creates `UserDatabase`
- dependency is hardcoded
- changing the source requires changing the class

Loose coupling in this project:

- `UserManager` depends on `UserDataProvider`
- dependency is provided from outside
- implementations can be swapped without changing `UserManager`

In short, loose coupling here is achieved by using an interface plus constructor injection.
