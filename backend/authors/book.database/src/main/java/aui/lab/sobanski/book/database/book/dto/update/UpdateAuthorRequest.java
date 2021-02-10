package aui.lab.sobanski.book.database.book.dto.update;

import aui.lab.sobanski.book.database.book.entity.Author;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * {@link Author}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAuthorRequest {

    private String name;
    private String birthDate;
    private String nationality;

    public static BiFunction<Author, UpdateAuthorRequest, Author> dtoToEntityUpdater(
            Function<String, LocalDate> localDateFunction) {

        return (author, request) -> {
            author.setName(Objects.requireNonNull(request.getName()));
            author.setBirthDate(localDateFunction.apply(Objects.requireNonNull(request.getBirthDate())));
            author.setNationality(Objects.requireNonNull(request.getNationality()));
            return author;
        };
    }
}
