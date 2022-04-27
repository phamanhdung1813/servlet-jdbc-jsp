package com.stanleypham.pagination;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageRequest implements Pageble {
    private Integer currentPage;
    private Integer visiblePages;
    private Sorter sorter;

    @Override
    public Integer getOffset() {
        if (this.currentPage != null) {
            return (this.currentPage - 1) * this.visiblePages;
        }
        return null;
    }

    @Override
    public Integer getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public Integer getLimit() {
        return this.visiblePages;
    }

    @Override
    public Sorter getSorter() {
        if (this.sorter != null) {
            return this.sorter;
        }
        return null;
    }
}
