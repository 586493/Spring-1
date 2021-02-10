package aui.lab.sobanski.book.database.book.dto.update;

import aui.lab.sobanski.book.database.book.entity.Category;
import lombok.*;

import java.util.Objects;
import java.util.function.BiFunction;

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
public class UpdateCategoryRequest {

    private String name;
    private String description;

    public static BiFunction<Category, UpdateCategoryRequest, Category> dtoToEntityUpdater() {
        return (category, request) ->
                Category.builder()
                        .id(category.getId())
                        .name(Objects.requireNonNull(request.getName()))
                        .description(Objects.requireNonNull(request.getDescription()))
                        .build();
    }
}
