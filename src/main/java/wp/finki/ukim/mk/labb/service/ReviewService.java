package wp.finki.ukim.mk.labb.service;

import wp.finki.ukim.mk.labb.model.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    public List<Review> findAll();
    public List<Review> findByTimeStamp(LocalDateTime time1, LocalDateTime time2);
    public Review save(String description,Integer Score, LocalDateTime timestamp, String bookID);
}
