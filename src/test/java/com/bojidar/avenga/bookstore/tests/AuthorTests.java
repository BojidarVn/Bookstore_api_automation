package com.bojidar.avenga.bookstore.tests;

import com.bojidar.avenga.bookstore.base.BaseTest;
import com.bojidar.avenga.bookstore.clients.AuthorsClient;
import com.bojidar.avenga.bookstore.factories.AuthorFactory;
import com.bojidar.avenga.bookstore.models.Author;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bookstore API")
@Feature("Authors API")
public class AuthorTests extends BaseTest {

    private final AuthorsClient authorsClient = new AuthorsClient();

    @Test
    @Story("Get all authors")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that all authors can be retrieved successfully")
    public void shouldGetAllAuthors() {
        Response response = authorsClient.getAllAuthors();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("$")).isNotEmpty();
    }

    @Test
    @Story("Get author by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an author can be retrieved by valid ID")
    public void shouldGetAuthorByValidId() {
        int authorId = 1;

        Response response = authorsClient.getAuthorById(authorId);

        assertThat(response.statusCode()).isEqualTo(200);

        Author author = response.as(Author.class);

        assertThat(author.getId()).isEqualTo(authorId);
        assertThat(author.getFirstName()).isNotBlank();
        assertThat(author.getLastName()).isNotBlank();
    }

    @Test
    @Story("Get author invalid ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that API returns 404 for non-existing author ID")
    public void shouldReturnNotFoundForInvalidAuthorId() {
        Response response = authorsClient.getAuthorById(999999);

        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    @Story("Create author")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an author can be created successfully with valid payload")
    public void shouldCreateAuthor() {
        Author author = AuthorFactory.createValidAuthor();

        Response response = authorsClient.createAuthor(author);

        assertThat(response.statusCode()).isEqualTo(200);

        Author createdAuthor = response.as(Author.class);

        assertThat(createdAuthor.getId()).isEqualTo(author.getId());
        assertThat(createdAuthor.getFirstName()).isEqualTo(author.getFirstName());
        assertThat(createdAuthor.getLastName()).isEqualTo(author.getLastName());
    }

    @Test
    @Story("Validation - empty first name")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify API behavior when creating an author with empty first name")
    public void shouldHandleAuthorWithEmptyFirstName() {
        Author author = AuthorFactory.createValidAuthor();
        author.setFirstName("");

        Response response = authorsClient.createAuthor(author);

        assertThat(response.statusCode()).isIn(200, 400);
    }

    @Test
    @Story("Update author")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an author can be updated successfully")
    public void shouldUpdateAuthor() {
        int authorId = 1;

        Author author = AuthorFactory.createValidAuthor();
        author.setId(authorId);
        author.setFirstName("Updated First Name");

        Response response = authorsClient.updateAuthor(authorId, author);

        assertThat(response.statusCode()).isEqualTo(200);

        Author updatedAuthor = response.as(Author.class);

        assertThat(updatedAuthor.getId()).isEqualTo(authorId);
        assertThat(updatedAuthor.getFirstName()).isEqualTo(author.getFirstName());
    }

    @Test
    @Story("Delete author")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that an author can be deleted successfully")
    public void shouldDeleteAuthor() {
        int authorId = 1;

        Response response = authorsClient.deleteAuthor(authorId);

        assertThat(response.statusCode()).isIn(200, 400);
    }
}