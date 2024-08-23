package wp.finki.ukim.mk.labb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import wp.finki.ukim.mk.labb.model.Author;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.BookStore;
import wp.finki.ukim.mk.labb.repository.jpa.AuthorRepository;
import wp.finki.ukim.mk.labb.repository.jpa.BookRepository;
import wp.finki.ukim.mk.labb.repository.jpa.BookStoreRepository;
import wp.finki.ukim.mk.labb.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookStoreRepository bookStoreRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addAuthorToBook(String authorId, String isbn) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(author.isEmpty() || book.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        Book foundBook = book.get();
        if(!foundBook.getAuthors().contains(author.get())) {
            foundBook.getAuthors().add(author.get());
            this.bookRepository.save(foundBook);
        }
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return this.bookRepository.findByIsbn(isbn);
    }

    @Override
    @Transactional
    public void save(String isbn, String title, String genre, Integer year, String bookStoreID) {
        BookStore bookStore = bookStoreRepository.findById(bookStoreID).orElseThrow();
        this.bookRepository.save(new Book(isbn, title, genre, year, bookStore));
    }

    @Override
    public Optional<Book> findByID(String id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void deleteByID(String id) {
        this.bookRepository.deleteById(id);
    }

}
