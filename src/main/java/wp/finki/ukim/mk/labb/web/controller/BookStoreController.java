package wp.finki.ukim.mk.labb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.BookStore;
import wp.finki.ukim.mk.labb.service.BookService;
import wp.finki.ukim.mk.labb.service.BookStoreService;

import java.util.List;

@Controller
@RequestMapping("/bookStores")
public class BookStoreController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookStoreController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    String getAllStores(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("stores",bookStoreService.findAll());
        return "listBookStore";
    }

    @GetMapping("/details/{id}")
    String bookStoreDetails(@PathVariable String id, Model model){
        if(bookStoreService.findByID(id).isPresent()){
            BookStore bookStore = bookStoreService.findByID(id).get();
            List<Book> books = bookService.listBooks();
            List<Book> booksFiltered = books.stream().filter(r -> r.getBookStore().getId().equals(id)).toList();
            model.addAttribute("books",booksFiltered);
            model.addAttribute("store",bookStore);
            return "bookStoreDetails";
        }
        return "redirect:/bookStores?error=Store+Not+Found";
    }
}
