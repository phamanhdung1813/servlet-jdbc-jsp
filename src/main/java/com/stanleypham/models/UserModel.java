package com.stanleypham.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel extends AbstractModel<UserModel> {
    private String username;
    private String password;
    private String fullName;
    private int status;
    private Long roleId;
    private RoleModel roleModel = new RoleModel();
}
