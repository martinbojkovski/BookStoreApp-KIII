package wp.finki.ukim.mk.labb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Document(collection = "authors")
public class Author {

    @Id
    private String id;  // Use String for MongoDB IDs, which are ObjectIds by default.

    private AuthorFullName fullName;

    private String biography;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author(AuthorFullName fullName, String biography, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }

    public Author() {
    }
}
