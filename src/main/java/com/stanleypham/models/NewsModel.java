package com.stanleypham.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsModel extends AbstractModel<NewsModel> {
    private String title;
    private String content;
    private Long categoryId;
}
