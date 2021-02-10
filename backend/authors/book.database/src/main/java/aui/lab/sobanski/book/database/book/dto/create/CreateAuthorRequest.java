package aui.lab.sobanski.book.database.book.dto.create;

import aui.lab.sobanski.book.database.book.entity.Author;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
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
public class CreateAuthorRequest {

    private String name;
    private String birthDate;
    private String nationality;

    public static Function<CreateAuthorRequest, Author> dtoToEntityMapper(Function<String, LocalDate> localDateFunction) {
        return request -> Author.builder()
                .name(Objects.requireNonNull(request.getName()))
                .birthDate(localDateFunction.apply(Objects.requireNonNull(request.getBirthDate())))
                .nationality(Objects.requireNonNull(request.getNationality()))
                .build();
    }

}
