package aui.lab.sobanski.book.database.book.dto.update;

import aui.lab.sobanski.book.database.book.entity.Book;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;

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
public class UpdateBookRequest {

    private String title;
    private String price;
    private String description;
    private String isbn;
    private Integer publicationYear;
    private Integer pages;

    public static BiFunction<Book, UpdateBookRequest, Book> dtoToEntityUpdater() {
        return (book, request) -> {
            book.setTitle(Objects.requireNonNull(request.getTitle()));
            book.setPrice(new BigDecimal(Objects.requireNonNull(request.getPrice())));
            book.setDescription(Objects.requireNonNull(request.getDescription()));
            book.setIsbn(Objects.requireNonNull(request.getIsbn()));
            book.setPublicationYear(Objects.requireNonNull(request.getPublicationYear()));
            book.setPages(Objects.requireNonNull(request.getPages()));
            return book;
        };
    }
}
