package com.stanleypham.mapper;

import com.stanleypham.models.RoleModel;
import com.stanleypham.models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetModelMapper<UserModel> {
    @Override
    public UserModel mappingResultSetToModel(ResultSet resultSet) {
        try {
            UserModel userModel = new UserModel();
            userModel.setUsername(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setFullName(resultSet.getString("fullname"));
            userModel.setStatus(resultSet.getInt("status"));
            try {
                RoleModel roleModel = new RoleModel();
                roleModel.setCode(resultSet.getString("code"));
                roleModel.setRole(resultSet.getString("role"));
                userModel.setRoleModel(roleModel);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            return userModel;
        } catch (SQLException e) {
            return null;
        }
    }
}