package com.stanleypham.dao.impl;

import com.stanleypham.dao.IUserDao;
import com.stanleypham.mapper.UserMapper;
import com.stanleypham.models.UserModel;

import java.util.List;

public class UserDao extends AbstractDao<UserModel> implements IUserDao {
    @Override
    public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
        String sql = "SELECT * FROM user INNER JOIN role ON role.id = user.roleid WHERE username = ? AND password = ? AND status = ?";
        List<UserModel> userModelList = sqlQuery(sql, new UserMapper(), username, password, status);
        return userModelList.isEmpty() ? null : userModelList.get(0);
    }
}
