package aui.lab.sobanski.book.database.book.dto.create;

import aui.lab.sobanski.book.database.book.entity.Category;
import lombok.*;

import java.util.Objects;
import java.util.function.Function;

/**
 * {@link Category}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCategoryRequest {

    private String name;
    private String description;

    public static Function<CreateCategoryRequest, Category> dtoToEntityMapper() {
        return request -> Category.builder()
                .name(Objects.requireNonNull(request.getName()))
                .description(Objects.requireNonNull(request.getDescription()))
                .build();
    }
}
