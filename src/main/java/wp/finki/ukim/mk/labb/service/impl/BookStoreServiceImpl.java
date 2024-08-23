package wp.finki.ukim.mk.labb.service.impl;

import org.springframework.stereotype.Service;
import wp.finki.ukim.mk.labb.model.BookStore;
import wp.finki.ukim.mk.labb.repository.impl.InMemoryBookStoreRepository;
import wp.finki.ukim.mk.labb.repository.jpa.BookStoreRepository;
import wp.finki.ukim.mk.labb.service.BookStoreService;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepository bookStoreRepository;


    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public Optional<BookStore> findByID(String id) {
        return bookStoreRepository.findById(id);
    }
}
