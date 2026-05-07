package com.bojidar.avenga.bookstore.clients;

import com.bojidar.avenga.bookstore.models.Book;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BooksClient {

    private static final String BASE_PATH = "/api/v1/Books";

    @Step("Get all books")
    public Response getAllBooks() {

        return given()
                .when()
                .get(BASE_PATH);
    }

    @Step("Get book by id: {id}")
    public Response getBookById(int id) {

        return given()
                .pathParam("id", id)
                .when()
                .get(BASE_PATH + "/{id}");
    }

    @Step("Create a new book")
    public Response createBook(Book book) {

        return given()
                .contentType("application/json")
                .body(book)
                .when()
                .post(BASE_PATH);
    }

    @Step("Update book with id: {id}")
    public Response updateBook(int invalidBookId, Book book) {

        return given()
                .contentType("application/json")
                .pathParam("id", invalidBookId)
                .body(book)
                .when()
                .put(BASE_PATH + "/{id}");
    }

    @Step("Delete book with id: {id}")
    public Response deleteBook(int id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .delete(BASE_PATH + "/{id}");
    }
}