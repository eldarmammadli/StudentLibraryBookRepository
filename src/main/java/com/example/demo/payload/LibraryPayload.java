package com.example.demo.payload;

import java.util.List;

public class LibraryPayload {

    private String name;

    private List<BookPayload> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookPayload> getBooks() {
        return books;
    }

    public void setBooks(List<BookPayload> books) {
        this.books = books;
    }
}
