package wp.finki.ukim.mk.labb.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.model.BookStore;

@Repository
public interface BookStoreRepository extends MongoRepository<BookStore,String> {
}
