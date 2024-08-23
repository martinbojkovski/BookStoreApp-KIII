package wp.finki.ukim.mk.labb.service.impl;

import org.springframework.stereotype.Service;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.Review;
import wp.finki.ukim.mk.labb.repository.jpa.BookRepository;
import wp.finki.ukim.mk.labb.repository.jpa.ReviewRepository;
import wp.finki.ukim.mk.labb.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public List<Review> findByTimeStamp(LocalDateTime time1, LocalDateTime time2) {
        return this.reviewRepository.findAllByTimestampBetween(time1,time2);
    }

    @Override
    public Review save(String description, Integer Score, LocalDateTime timestamp, String bookID) {
        Book book = bookRepository.findById(bookID).get();
        return this.reviewRepository.save(new Review(Score,description,book,timestamp));
    }
}
