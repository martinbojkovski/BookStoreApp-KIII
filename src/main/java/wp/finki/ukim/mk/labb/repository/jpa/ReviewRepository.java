package wp.finki.ukim.mk.labb.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.model.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findAllByTimestampBetween(LocalDateTime time1, LocalDateTime time2);
}
