package wp.finki.ukim.mk.labb.repository.impl;

import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.bootstrap.DataHolder;
import wp.finki.ukim.mk.labb.model.Author;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.BookStore;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }

    public Book findByIsbn(String isbn){
        return DataHolder.books.stream().filter(r -> r.getIsbn().equals(isbn)).findFirst().orElseThrow();
    }

    public Optional<Book> findByID(String id){
        return DataHolder.books.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Author addAuthorToBook(Author author, Book book) {
        book.getAuthors().removeIf(i -> i.getId().equals(author.getId()));
        book.getAuthors().add(author);
        return author;
    }

    public Optional<Book> save(String isbn,String title, String genre, String year, BookStore bookStore){
        DataHolder.books.removeIf(i -> i.getIsbn().equals(isbn));
        Book book = new Book(isbn,title,genre,Integer.parseInt(year),bookStore);
        DataHolder.books.add(book);
        return Optional.of(book);
    }

    public void deleteByID(String id){
        DataHolder.books.removeIf(r -> r.getId().equals(id));
    }
}
