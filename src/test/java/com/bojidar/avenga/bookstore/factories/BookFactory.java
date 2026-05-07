package com.bojidar.avenga.bookstore.factories;

import com.bojidar.avenga.bookstore.models.Book;

import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class BookFactory {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    private BookFactory() {
    }

    public static Book createValidBook() {

        return Book.builder()
                .id(ID_GENERATOR.getAndIncrement())
                .title("Avenga API Automation Book")
                .description("Book created for API automation assessment")
                .pageCount(250)
                .excerpt("Sample excerpt")
                .publishDate(OffsetDateTime.now().toString())
                .build();
    }
}