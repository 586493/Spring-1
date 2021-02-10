package aui.lab.sobanski.book.database.book.dto.create;

import aui.lab.sobanski.book.database.book.entity.Author;
import lombok.*;

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

    private Long id;

    public static Function<CreateAuthorRequest, Author> dtoToEntityMapper() {
        return request -> Author.builder()
                .id(Objects.requireNonNull(request.getId()))
                .build();
    }

}
