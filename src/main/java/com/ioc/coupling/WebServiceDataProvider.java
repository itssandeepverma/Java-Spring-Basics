package com.ioc.coupling;

public class WebServiceDataProvider implements UserDataProvider {

    @Override
    public String getUserDetails() {

        return "User Details from the Web Service";
    }
}
