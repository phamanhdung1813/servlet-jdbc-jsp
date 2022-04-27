package com.stanleypham.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleModel extends AbstractModel<RoleModel> {
    private String role;
    private String code;
}
