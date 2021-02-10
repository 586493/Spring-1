package aui.lab.sobanski.book.database.book.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString(exclude = "description")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "categories")
public class Category {

    /**
     * Unique id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

}
