package wp.finki.ukim.mk.labb.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import wp.finki.ukim.mk.labb.model.Author;
import wp.finki.ukim.mk.labb.model.AuthorFullName;
import wp.finki.ukim.mk.labb.model.Book;
import wp.finki.ukim.mk.labb.model.BookStore;
import org.springframework.stereotype.Component;
import wp.finki.ukim.mk.labb.repository.jpa.BookStoreRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataHolder {

    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<BookStore> bookStores = new ArrayList<>();
    public final BookStoreRepository bookStoreRepository;

    @PostConstruct
    public void init()
    {
        authors.add(new Author(new AuthorFullName("Martin", "Bojkovski"),"Bio1", LocalDate.of(2002,4,10)));
        authors.add(new Author(new AuthorFullName("Martin", "B"),"Bio2", LocalDate.of(2002,4,10)));
        authors.add(new Author(new AuthorFullName("Martin", "B2"),"Bio3", LocalDate.of(2002,4,10)));
        authors.add(new Author(new AuthorFullName("Martin", "B3"),"Bio4", LocalDate.of(2002,4,10)));
        authors.add(new Author(new AuthorFullName("Martin", "B4"),"Bio5", LocalDate.of(2002,4,10)));


        BookStore bookS = new BookStore("Store1","Kumanovo","123");
        bookStores.add(bookS);
        BookStore bookA = new BookStore("Store2","Skopje","345");
        bookStores.add(bookA);
        BookStore bookB = new BookStore("Store3","Ohrid","567");
        bookStores.add(bookB);


        books.add(new Book("11111","Batman","Action",2010,bookS));
        books.add(new Book("22222","Spiderman","Action",2015,bookA));
        books.add(new Book("33333","Hary Potter","Drama",2003,bookS));
        books.add(new Book("44444","Inferno","Comedy",2010,bookA));
        books.add(new Book("55555","Anna Karenina","Realisam",2010,bookB));

    }
}

