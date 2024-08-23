package wp.finki.ukim.mk.labb.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.BookStore;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    Optional<Book> findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
