package wp.finki.ukim.mk.labb.web.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.Review;
import wp.finki.ukim.mk.labb.service.BookService;
import wp.finki.ukim.mk.labb.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping
    public String AllReviews(@RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm:ss") LocalDateTime t1,
                             @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm:ss") LocalDateTime t2,
                                 Model model){
        if(t1 != null && t2 != null){
            model.addAttribute("reviews",reviewService.findByTimeStamp(t1,t2));
        }
        else{
            model.addAttribute("reviews",reviewService.findAll());

            List<Review> reviews = this.reviewService.findAll();

            Optional<Map.Entry<String, Double>> maxAverageEntry =  reviews.stream().
                    collect(Collectors.groupingBy(r -> r.getBook().getIsbn(),Collectors.averagingDouble(Review::getScore)))
                    .entrySet().stream().max(Map.Entry.comparingByValue());

            if (maxAverageEntry.isPresent()) {
                String isbnWithMaxAverage = maxAverageEntry.get().getKey();
                double maxAverageScore = maxAverageEntry.get().getValue();

                model.addAttribute("bookMax",bookService.findBookByIsbn(isbnWithMaxAverage));
                model.addAttribute("maxAvgScore", maxAverageScore);
            }

        }
        return "reviews-list";
    }

    @GetMapping("/add-review/{bookId}")
    public String AddReviewForm(@PathVariable String bookId, Model model){
        model.addAttribute("book",bookService.findByID(bookId));
        model.addAttribute("bookID",bookId);
        return "add-review";
    }

    @PostMapping("/add")
    public String AddReview(@RequestParam String bookID,
                            @RequestParam String description,
                            @RequestParam Integer score,
                            @RequestParam("time")@DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm:ss") LocalDateTime time){
        this.reviewService.save(description,score,time,bookID);
        return "redirect:/reviews";
    }

    @PostMapping("/search")
    public String Search(@RequestParam("time1")@DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm:ss") LocalDateTime time1,
                         @RequestParam("time2")@DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm:ss") LocalDateTime time2){
        return "redirect:/reviews?t1="+time1+"&t2="+time2;
    }
}
