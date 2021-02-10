package aui.lab.sobanski.book.database.book.event.dto;

import aui.lab.sobanski.book.database.book.entity.Author;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateAuthorRequest {

    private Long id;

    public static Function<Author, CreateAuthorRequest> entityToDtoMapper() {
        return author -> CreateAuthorRequest.builder()
                .id(author.getId())
                .build();
    }
}
