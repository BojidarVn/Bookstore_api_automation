package com.bojidar.avenga.bookstore.factories;

import com.bojidar.avenga.bookstore.models.Author;

import java.util.concurrent.atomic.AtomicInteger;

public class AuthorFactory {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    private AuthorFactory() {
    }

    public static Author createValidAuthor() {
        int id = ID_GENERATOR.getAndIncrement();

        return Author.builder()
                .id(id)
                .idBook(1)
                .firstName("FirstName " + id)
                .lastName("LastName " + id)
                .build();
    }
}