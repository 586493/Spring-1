package aui.lab.sobanski.book.database.book.dto.get.all;

import lombok.*;

import java.util.Collection;
import java.util.List;
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
public class GetAuthorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Author {
        private Long id;
        private String name;

    }

    @Singular
    private List<Author> authors;

    public static Function<Collection<aui.lab.sobanski.book.database.book.entity.Author>, GetAuthorsResponse> entityToDtoMapper() {
        return authors -> {
            GetAuthorsResponseBuilder response = GetAuthorsResponse.builder();
            authors.stream()
                    .map(author -> Author.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .build())
                    .forEach(response::author);
            return response.build();
        };
    }
}
