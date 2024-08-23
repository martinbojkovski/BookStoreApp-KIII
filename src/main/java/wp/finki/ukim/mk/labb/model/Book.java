package wp.finki.ukim.mk.labb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id; // MongoDB uses String IDs by default
    private String isbn;
    private String title;
    private String genre;
    private Integer year;

    @DBRef
    @Field(name = "authors")
    private List<Author> authors = new ArrayList<>();

    @DBRef
    @Field(name = "book_store")
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, Integer year, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore = bookStore;
    }

    public Book() {
    }
}
