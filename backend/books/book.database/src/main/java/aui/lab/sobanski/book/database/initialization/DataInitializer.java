package aui.lab.sobanski.book.database.initialization;


import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.entity.Book;
import aui.lab.sobanski.book.database.book.entity.Category;
import aui.lab.sobanski.book.database.book.service.AuthorService;
import aui.lab.sobanski.book.database.book.service.BookService;
import aui.lab.sobanski.book.database.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class DataInitializer {

    private final AuthorService authorService;

    private final BookService bookService;

    private final CategoryService categoryService;

    @Autowired
    public DataInitializer(AuthorService authorService,
                           BookService bookService,
                           CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @PostConstruct
    private synchronized void init() {
        Author author1 = Author.builder().id(1L).build();
        Author author2 = Author.builder().id(2L).build();
        Author author3 = Author.builder().id(3L).build();
        authorService.create(author1);
        authorService.create(author2);
        authorService.create(author3);


        Category category1 = Category.builder().id(1L).build();
        Category category2 = Category.builder().id(2L).build();
        Category category3 = Category.builder().id(3L).build();
        categoryService.create(category1);
        categoryService.create(category2);
        categoryService.create(category3);

        Book book1 = Book.builder()
                .title("Blood of Elves")
                .author(author1)
                .category(category1)
                .price(new BigDecimal("8.99")) // $8.99
                .publicationYear(2009)
                .description("The Witcher, Geralt of Rivia, becomes the guardian of Ciri, " +
                        "surviving heiress of a bloody revolution and prophesied savior of the world, " +
                        "in the first novel of the New York Times bestselling series that inspired " +
                        "the Netflix show and the hit video games.")
                .isbn("978-0316029193")
                .pages(320)
                .build();
        bookService.create(book1);

        Book book2 = Book.builder()
                .title("Solaris")
                .author(author2)
                .category(category2)
                .price(new BigDecimal("12.99")) // $12.99
                .publicationYear(2002)
                .description("When psychologist Kris Kelvin arrives at the planet Solaris to study " +
                        "the ocean that covers its surface, he finds himself confronting a painful memory " +
                        "embodied in the physical likeness of a past lover. Kelvin learns that " +
                        "he is not alone in this and that other crews examining the planet " +
                        "are plagued with their own repressed and newly real memories. " +
                        "Could it be, as Solaris scientists speculate, that the ocean " +
                        "may be a massive neural center creating these memories, " +
                        "for a reason no one can identify?")
                .isbn("978-0156027601")
                .pages(224)
                .build();
        bookService.create(book2);

        Book book3 = Book.builder()
                .title("The Deluge")
                .author(author3)
                .category(category3)
                .price(new BigDecimal("9.99")) // $9.99
                .publicationYear(2019)
                .description("The Deluge is a historical novel by the Polish author Henryk Sienkiewicz, " +
                        "published in 1886. It is the second volume of a three-volume series " +
                        "known to Poles as \"The Trilogy,\" having been preceded by With Fire and Sword " +
                        "and followed by Fire in the Steppe. The novel tells a story of a fictional " +
                        "Polish-Lithuanian Commonwealth soldier and noble Andrzej Kmicic and shows " +
                        "a panorama of the Commonwealth during its historical period of the Deluge, " +
                        "which was a part of the Northern Wars. ")
                .isbn("9788373272255")
                .pages(936)
                .build();
        bookService.create(book3);
    }

}
