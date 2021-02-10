package aui.lab.sobanski.book.database.book.dto.get.one;

import aui.lab.sobanski.book.database.book.entity.Author;
import lombok.*;

import java.time.LocalDate;
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
public class GetAuthorResponse {

    private Long id;
    private String name;
    private String birthDate;
    private String nationality;

    public static Function<Author, GetAuthorResponse> entityToDtoMapper(Function<LocalDate, String> stringFunction) {
        return author -> GetAuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .birthDate(stringFunction.apply(author.getBirthDate()))
                .nationality(author.getNationality())
                .build();
    }
}
