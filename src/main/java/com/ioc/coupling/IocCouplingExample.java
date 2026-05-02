package com.ioc.coupling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocCouplingExample {

    public static void main(String[] args) {

//        UserDataProvider databaseProvider = new UserDatabaseProvider();
//        UserManager userManager = new UserManager(databaseProvider);
//        System.out.println(userManager.getUserInfo());

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationIoCLooseCouplingExample.xml");
        UserManager userManagerwithDB = (UserManager) applicationContext.getBean("userManagerWithUserDataProvider");
        System.out.println(userManagerwithDB.getUserInfo());

//        UserDataProvider webDatabaseProvider = new WebDatabaseProvider();
//        UserManager webDatabaseManager = new UserManager(webDatabaseProvider);
//        System.out.println(webDatabaseManager.getUserInfo());

        UserManager userManagerWithWS = (UserManager) applicationContext.getBean("userManagerWithWebServiceProvider");
        System.out.println(userManagerWithWS.getUserInfo());

    }

}
