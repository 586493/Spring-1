package aui.lab.sobanski.book.database.book.dto.get.all;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * {@link Book}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBooksResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Book {
        private Long id;
        private String title;

    }

    @Singular
    private List<Book> books;

    public static Function<Collection<aui.lab.sobanski.book.database.book.entity.Book>, GetBooksResponse> entityToDtoMapper() {
        return books -> {
            GetBooksResponseBuilder response = GetBooksResponse.builder();
            books.stream()
                    .map(book -> Book.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .build())
                    .forEach(response::book);
            return response.build();
        };
    }
}
