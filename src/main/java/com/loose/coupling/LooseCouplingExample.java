package com.loose.coupling;

public class LooseCouplingExample {

    public static void main(String[] args) {

        UserDataProvider databaseProvider = new UserDatabaseProvider();
        UserManager userManager = new UserManager(databaseProvider);
        System.out.println(userManager.getUserInfo());

        UserDataProvider webDatabaseProvider = new WebDatabaseProvider();
        UserManager webDatabaseManager = new UserManager(webDatabaseProvider);
        System.out.println(webDatabaseManager.getUserInfo());

    }

}
