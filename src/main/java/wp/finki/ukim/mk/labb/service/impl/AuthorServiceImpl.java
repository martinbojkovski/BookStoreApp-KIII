package wp.finki.ukim.mk.labb.service.impl;

import org.springframework.stereotype.Service;
import wp.finki.ukim.mk.labb.model.Author;
import wp.finki.ukim.mk.labb.repository.impl.InMemoryAuthorRepository;
import wp.finki.ukim.mk.labb.repository.jpa.AuthorRepository;
import wp.finki.ukim.mk.labb.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(String id) {
        return authorRepository.findById(id).orElseThrow();
    }
}
