package com.stanleypham.services.impl;

import com.stanleypham.dao.IUserDao;
import com.stanleypham.models.UserModel;
import com.stanleypham.services.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    private IUserDao iUserDao;

    @Override
    public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
        return iUserDao.findByUsernameAndPasswordAndStatus(username, password, status);
    }
}
