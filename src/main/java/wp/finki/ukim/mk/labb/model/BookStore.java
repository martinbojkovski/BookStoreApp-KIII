package wp.finki.ukim.mk.labb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "book_stores")
public class BookStore {

    @Id
    private String id; // MongoDB uses String IDs by default
    private String name;
    private String city;
    private String address;

    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public BookStore() {
    }
}
