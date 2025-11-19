package com.vekrest.entity;

public class Pagination<T> {
    public final Iterable<T> content;
    public final int pageNumber;
    public final int pageSize;
    public final long totalElements;
    public final int totalPages;

    public Pagination(
            Iterable<T> content,
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages
    ) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}