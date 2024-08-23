package wp.finki.ukim.mk.labb.repository.impl;

import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.bootstrap.DataHolder;
import wp.finki.ukim.mk.labb.model.Author;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    public Optional<Author> findById(String id) {
        return DataHolder.authors.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
