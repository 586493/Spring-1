package aui.lab.sobanski.book.database.book.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@ToString(exclude = {"description", "publicationYear", "pages"})
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "books")
public class Book {

    /**
     * Unique id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    private BigDecimal price;

    @Lob
    private String description;

    /**
     * International Standard Book Number
     */
    private String isbn;

    @Column(name = "publication_year")
    private int publicationYear;

    private int pages;

}
