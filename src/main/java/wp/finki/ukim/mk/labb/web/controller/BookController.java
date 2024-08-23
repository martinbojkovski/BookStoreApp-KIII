package wp.finki.ukim.mk.labb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.service.BookService;
import wp.finki.ukim.mk.labb.service.BookStoreService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService,BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Book> books = bookService.listBooks();
        model.addAttribute("books",books);
        return "listBooks";
    }

    @PostMapping
    public String selectBook(@RequestParam Long bookID){

        return "redirect:/authors?bookID="+bookID;
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model){
        model.addAttribute("stores",bookStoreService.findAll());
        return "add-book";
    }

    @PostMapping("/add")
    public String SaveBook(@RequestParam String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam String bookStore){
        this.bookService.save(isbn,title,genre,year,bookStore);
        return "redirect:/books";
    }

    @GetMapping("/edit/{bookId}")
    public String editBook(@PathVariable String bookId, Model model){
        if(this.bookService.findByID(bookId).isPresent()){
            Book book = this.bookService.findByID(bookId).get();
            model.addAttribute("book",book);
            model.addAttribute("stores",bookStoreService.findAll());
            return "add-book";
        }
        return "redirect:/books?error=Book+Not+Found";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        if(this.bookService.findByID(id).isPresent()) {
            this.bookService.deleteByID(id);
        }

        return "redirect:/books";
    }
}

