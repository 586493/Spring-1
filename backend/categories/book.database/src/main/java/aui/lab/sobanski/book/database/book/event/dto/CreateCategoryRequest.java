package aui.lab.sobanski.book.database.book.event.dto;

import aui.lab.sobanski.book.database.book.entity.Category;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCategoryRequest {

    private Long id;

    public static Function<Category, CreateCategoryRequest> entityToDtoMapper() {
        return category -> CreateCategoryRequest.builder()
                .id(category.getId())
                .build();
    }
}
