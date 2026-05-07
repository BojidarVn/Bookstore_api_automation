package com.bojidar.avenga.bookstore.tests;

import com.bojidar.avenga.bookstore.base.BaseTest;
import com.bojidar.avenga.bookstore.clients.BooksClient;
import com.bojidar.avenga.bookstore.factories.BookFactory;
import com.bojidar.avenga.bookstore.models.Book;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bookstore API")
@Feature("Books API")
public class BookTests extends BaseTest {

    private final BooksClient booksClient = new BooksClient();

    @Test
    @Story("Get all books")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that all books can be retrieved successfully")
    public void shouldGetAllBooks() {
        Response response = booksClient.getAllBooks();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("$")).isNotEmpty();
    }

    @Test
    @Story("Create book")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a book can be created successfully with a valid request payload.")
    public void shouldCreateBookWithValidPayload() {
        Book book = BookFactory.createValidBook();

        Response response = booksClient.createBook(book);

        assertThat(response.statusCode()).isEqualTo(200);

        Book createdBook = response.as(Book.class);

        assertThat(createdBook.getId()).isEqualTo(book.getId());
        assertThat(createdBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(createdBook.getDescription()).isEqualTo(book.getDescription());
        assertThat(createdBook.getPageCount()).isEqualTo(book.getPageCount());
        assertThat(createdBook.getExcerpt()).isEqualTo(book.getExcerpt());
    }

    @Test
    @Story("Validation - empty title")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify API behavior when creating a book with empty title")
    public void shouldHandleBookWithEmptyTitle() {
        Book book = BookFactory.createValidBook();
        book.setTitle("");

        Response response = booksClient.createBook(book);

        assertThat(response.statusCode()).isIn(200, 400);
    }

    @Test
    @Story("Validation - negative page count")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify API behavior when creating a book with negative page count")
    public void shouldHandleBookWithNegativePageCount() {
        Book book = BookFactory.createValidBook();
        book.setPageCount(-1);

        Response response = booksClient.createBook(book);

        assertThat(response.statusCode()).isIn(200, 400);
    }

    @Test
    @Story("Validation - invalid publish date")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify API behavior when creating a book with invalid publish date")
    public void shouldHandleBookWithInvalidPublishDate() {
        Book book = BookFactory.createValidBook();
        book.setPublishDate("invalid-date");

        Response response = booksClient.createBook(book);

        assertThat(response.statusCode()).isIn(200, 400);
    }

    @Test
    @Story("Get book invalid ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that API returns 404 for non-existing book ID")
    public void shouldReturnNotFoundForInvalidBookId() {
        Response response = booksClient.getBookById(999999);

        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    @Story("Get book by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a book can be retrieved successfully by valid ID")
    public void shouldGetBookByValidId() {

        int bookId = 1;

        Response response = booksClient.getBookById(bookId);

        assertThat(response.statusCode()).isEqualTo(200);

        Book book = response.as(Book.class);

        assertThat(book.getId()).isEqualTo(bookId);
        assertThat(book.getTitle()).isNotBlank();
        assertThat(book.getDescription()).isNotBlank();
        assertThat(book.getPageCount()).isGreaterThan(0);
    }

    @Test
    @Story("Update book")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an existing book can be updated successfully with valid payload")
    public void shouldUpdateBookWithValidPayload() {

        int bookId = 1;

        Book book = BookFactory.createValidBook();
        book.setId(bookId);
        book.setTitle("Updated Avenga Book");
        book.setDescription("Updated book description");
        book.setPageCount(350);

        Response response = booksClient.updateBook(bookId, book);

        assertThat(response.statusCode()).isEqualTo(200);

        Book updatedBook = response.as(Book.class);

        assertThat(updatedBook.getId()).isEqualTo(bookId);
        assertThat(updatedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(updatedBook.getDescription()).isEqualTo(book.getDescription());
        assertThat(updatedBook.getPageCount()).isEqualTo(book.getPageCount());
    }

    @Test
    @Story("Update book invalid ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify API behavior when updating a book with non-existing ID")
    public void shouldHandleUpdateBookWithInvalidId() {

        int invalidBookId = 999999;

        Book book = BookFactory.createValidBook();
        book.setId(invalidBookId);

        Response response = booksClient.updateBook(invalidBookId, book);

        assertThat(response.statusCode()).isIn(200, 404);
    }

    @Test
    @Story("Delete book")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a book can be deleted successfully by valid ID")
    public void shouldDeleteBookByValidId() {

        int bookId = 1;

        Response response = booksClient.deleteBook(bookId);

        assertThat(response.statusCode()).isEqualTo(200);
    }
}