package wp.finki.ukim.mk.labb.service;

import wp.finki.ukim.mk.labb.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(String id);
}
