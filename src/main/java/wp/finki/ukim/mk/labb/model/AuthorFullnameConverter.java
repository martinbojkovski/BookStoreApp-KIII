package wp.finki.ukim.mk.labb.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullName,String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(AuthorFullName authorFullName) {
        if (authorFullName == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (authorFullName.getName() != null
                && !authorFullName.getName().isEmpty()) {
            sb.append(authorFullName.getName());
            sb.append(SEPARATOR);
        }

        if (authorFullName.getSurname() != null && !authorFullName.getSurname()
                .isEmpty()) {
            sb.append(authorFullName.getSurname());
        }

        return sb.toString();
    }

    @Override
    public AuthorFullName convertToEntityAttribute(String dbAuthorName) {
        if (dbAuthorName == null || dbAuthorName.isEmpty()) {
            return null;
        }

        String[] pieces = dbAuthorName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        AuthorFullName authorFullName = new AuthorFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbAuthorName.contains(SEPARATOR)) {
            authorFullName.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                authorFullName.setName(pieces[1]);
            }
        } else {
            authorFullName.setName(firstPiece);
        }

        return authorFullName;
    }
}
