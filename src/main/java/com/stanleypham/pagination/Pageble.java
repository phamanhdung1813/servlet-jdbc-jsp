package com.stanleypham.pagination;

public interface Pageble {
    Integer getOffset();
    Integer getCurrentPage();
    Integer getLimit();
    Sorter getSorter();
}
