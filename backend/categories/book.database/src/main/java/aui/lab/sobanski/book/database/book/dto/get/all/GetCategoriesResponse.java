package aui.lab.sobanski.book.database.book.dto.get.all;

import lombok.*;

import java.util.Collection;
import java.util.List;
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
public class GetCategoriesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Category {
        private Long id;
        private String name;

    }

    @Singular
    private List<Category> categories;

    public static Function<Collection<aui.lab.sobanski.book.database.book.entity.Category>, GetCategoriesResponse> entityToDtoMapper() {
        return categories -> {
            GetCategoriesResponseBuilder response = GetCategoriesResponse.builder();
            categories.stream()
                    .map(category -> Category.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build())
                    .forEach(response::category);
            return response.build();
        };
    }
}
