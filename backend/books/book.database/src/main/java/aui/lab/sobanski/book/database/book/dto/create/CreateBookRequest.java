package aui.lab.sobanski.book.database.book.dto.create;

import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.entity.Book;
import aui.lab.sobanski.book.database.book.entity.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
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
public class CreateBookRequest {

    private String title;
    private Long authorId;
    private Long categoryId;
    private String price;
    private String description;
    private String isbn;
    private Integer publicationYear;
    private Integer pages;

    public static Function<CreateBookRequest, Book> dtoToEntityMapper(Function<Long, Category> categoryFunction,
                                                                      Function<Long, Author> authorFunction) {
        return request -> Book.builder()
                .title(Objects.requireNonNull(request.getTitle()))
                .author(authorFunction.apply(Objects.requireNonNull(request.getAuthorId())))
                .category(categoryFunction.apply(Objects.requireNonNull(request.getCategoryId())))
                .price(new BigDecimal(Objects.requireNonNull(request.getPrice())))
                .description(Objects.requireNonNull(request.getDescription()))
                .isbn(Objects.requireNonNull(request.getIsbn()))
                .publicationYear(Objects.requireNonNull(request.getPublicationYear()))
                .pages(Objects.requireNonNull(request.getPages()))
                .build();
    }
}
