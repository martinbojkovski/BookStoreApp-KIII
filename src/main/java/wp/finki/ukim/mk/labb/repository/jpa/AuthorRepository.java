package wp.finki.ukim.mk.labb.repository.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author,String> {

}
