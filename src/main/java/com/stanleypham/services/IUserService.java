package com.stanleypham.services;

import com.stanleypham.models.UserModel;

public interface IUserService {
    UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
