package aui.lab.sobanski.book.database.book.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "authors")
public class Author {

    /**
     * Unique id (primary key).
     */
    @Id
    private Long id;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> books;
}
