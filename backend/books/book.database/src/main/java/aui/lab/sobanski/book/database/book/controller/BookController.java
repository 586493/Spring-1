package aui.lab.sobanski.book.database.book.controller;

import aui.lab.sobanski.book.database.book.dto.create.CreateBookRequest;
import aui.lab.sobanski.book.database.book.dto.get.all.GetBooksResponse;
import aui.lab.sobanski.book.database.book.dto.get.one.GetBookResponse;
import aui.lab.sobanski.book.database.book.dto.update.UpdateBookRequest;
import aui.lab.sobanski.book.database.book.entity.Book;
import aui.lab.sobanski.book.database.book.service.AuthorService;
import aui.lab.sobanski.book.database.book.service.BookService;
import aui.lab.sobanski.book.database.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final static String AUTHOR_OR_CATEGORY_NOT_FOUND = "Author or category not found.";

    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks() {
        return ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("id") long id) {
        return bookService.find(id)
                .map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody CreateBookRequest request, UriComponentsBuilder builder) {
        try {
            Book book = CreateBookRequest
                    .dtoToEntityMapper(categoryId -> categoryService.find(categoryId).orElseThrow(),
                            authorId -> authorService.find(authorId).orElseThrow())
                    .apply(request);
            bookService.create(book);
            return ResponseEntity.created(builder.pathSegment("api", "books", "{id}")
                    .buildAndExpand(book.getId()).toUri()).build();
        } catch (NullPointerException | NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AUTHOR_OR_CATEGORY_NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateBook(@RequestBody UpdateBookRequest request, @PathVariable("id") long id) {
        try {
            Optional<Book> book = bookService.find(id);
            if (book.isPresent()) {
                UpdateBookRequest.dtoToEntityUpdater().apply(book.get(), request);
                bookService.update(book.get());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NullPointerException | NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        Optional<Book> book = bookService.find(id);
        if (book.isPresent()) {
            bookService.delete(book.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
