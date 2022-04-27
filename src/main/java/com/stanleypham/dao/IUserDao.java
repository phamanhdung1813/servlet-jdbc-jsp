package com.stanleypham.dao;

import com.stanleypham.models.UserModel;

public interface IUserDao extends GenericDao<UserModel> {
    UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
