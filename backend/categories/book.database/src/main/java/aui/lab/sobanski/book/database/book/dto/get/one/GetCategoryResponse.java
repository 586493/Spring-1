package aui.lab.sobanski.book.database.book.dto.get.one;

import aui.lab.sobanski.book.database.book.entity.Category;
import lombok.*;

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
public class GetCategoryResponse {

    private Long id;
    private String name;
    private String description;

    public static Function<Category, GetCategoryResponse> entityToDtoMapper() {
        return category -> GetCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
