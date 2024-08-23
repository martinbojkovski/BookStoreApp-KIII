package wp.finki.ukim.mk.labb.service;

import wp.finki.ukim.mk.labb.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    void addAuthorToBook(String authorId, String isbn);
    Optional<Book> findBookByIsbn(String isbn);

    void save(String isbn, String title, String genre, Integer year, String bookStoreID);

    Optional<Book> findByID(String id);

    void deleteByID(String id);

}
