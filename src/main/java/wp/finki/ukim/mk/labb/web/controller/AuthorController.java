package wp.finki.ukim.mk.labb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.service.AuthorService;
import wp.finki.ukim.mk.labb.service.BookService;
import wp.finki.ukim.mk.labb.service.BookStoreService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    public final AuthorService authorService;
    public final BookService bookService;
    public final BookStoreService bookStoreService;

    public AuthorController(AuthorService authorService, BookService bookService, BookStoreService bookStoreService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getAuthors(@RequestParam String bookID, Model model){
        model.addAttribute("authors",authorService.listAuthors());
        Book book = bookService.findByID(bookID).get();
        model.addAttribute("book",book);
        return "authorList";
    }

    @PostMapping("{id}")
    public String addAuthor(@RequestParam String authorId, @PathVariable String id, Model model){
        Book book = bookService.findByID(id).get();
        bookService.addAuthorToBook(authorId,book.getIsbn());
        model.addAttribute("book",book);
        return "bookDetails";
    }
}
