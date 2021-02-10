package aui.lab.sobanski.book.database.book.dto.get.one;

import aui.lab.sobanski.book.database.book.entity.Book;
import lombok.*;

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
public class GetBookResponse {

    private Long id;
    private String title;
    private Long author;
    private Long category;
    private String price;
    private String description;
    private String isbn;
    private Integer publicationYear;
    private Integer pages;

    public static Function<Book, GetBookResponse> entityToDtoMapper() {
        return book -> GetBookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor().getId())
                .category(book.getCategory().getId())
                .price(book.getPrice().toPlainString())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .pages(book.getPages())
                .build();
    }
}
