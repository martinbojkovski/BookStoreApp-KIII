package wp.finki.ukim.mk.labb.service;

import wp.finki.ukim.mk.labb.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    public List<BookStore> findAll();

    Optional<BookStore> findByID(String id);
}
