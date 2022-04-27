package com.stanleypham.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryModel extends AbstractModel<CategoryModel> {
    private String code;
    private String name;
}
