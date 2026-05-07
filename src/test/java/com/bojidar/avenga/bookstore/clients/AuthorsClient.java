package com.bojidar.avenga.bookstore.clients;

import com.bojidar.avenga.bookstore.models.Author;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorsClient {

    private static final String BASE_PATH = "/api/v1/Authors";

    @Step("Get all authors")
    public Response getAllAuthors() {
        return given()
                .when()
                .get(BASE_PATH);
    }

    @Step("Get author by id: {id}")
    public Response getAuthorById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(BASE_PATH + "/{id}");
    }

    @Step("Create a new author")
    public Response createAuthor(Author author) {
        return given()
                .contentType("application/json")
                .body(author)
                .when()
                .post(BASE_PATH);
    }

    @Step("Update author with id: {id}")
    public Response updateAuthor(int id, Author author) {
        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(author)
                .when()
                .put(BASE_PATH + "/{id}");
    }

    @Step("Delete author with id: {id}")
    public Response deleteAuthor(int id) {
        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .delete(BASE_PATH + "/{id}");
    }
}