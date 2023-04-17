package org.example.service;

import org.example.aop.UserLoggedIn;

import javax.inject.Singleton;

@Singleton
public class ApiServiceImpl implements ApiService {
    @Override
    @UserLoggedIn
    public String getServiceDescription() {
        return "This is service description";
    }
}
