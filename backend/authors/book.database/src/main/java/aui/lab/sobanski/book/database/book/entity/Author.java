package aui.lab.sobanski.book.database.book.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@ToString()
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "authors")
public class Author {

    /**
     * Unique id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * ISO 3166-1 alpha-3 (three-letter country codes)
     */
    private String nationality;

}
