package com.stanleypham.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AbstractModel<T> {
    private Long id;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private long[] ids;
    private List<T> attributeResultList = new ArrayList<>();
    private int visiblePages;
    private int totalPages;
    private int totalItems;
    private int currentPage;
    private String sortName;
    private String sortBy;
    private String type;
}
