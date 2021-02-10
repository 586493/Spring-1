package aui.lab.sobanski.book.database.initialization;


import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    @Autowired
    public DataInitializer(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostConstruct
    private synchronized void init() {
        Author author1 = Author.builder()
                .name("Andrzej Sapkowski")
                .birthDate(LocalDate.of(1948, 6, 21)) // 21 June 1948
                .nationality("POL")
                .build();
        authorService.create(author1);

        Author author2 = Author.builder()
                .name("Stanisław Lem")
                .birthDate(LocalDate.of(1921, 9, 13)) // 13 September 1921
                .nationality("POL")
                .build();
        authorService.create(author2);

        Author author3 = Author.builder()
                .name("Henryk Sienkiewicz")
                .birthDate(LocalDate.of(1846, 5, 5)) // 5 May 1846
                .nationality("POL")
                .build();
        authorService.create(author3);
    }

}
