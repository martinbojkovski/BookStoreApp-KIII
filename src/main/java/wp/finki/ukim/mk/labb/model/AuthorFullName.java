package wp.finki.ukim.mk.labb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorFullName implements Serializable {
    private String surname;
    private String name;
}
