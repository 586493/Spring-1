package aui.lab.sobanski.book.database.book.controller;

import aui.lab.sobanski.book.database.book.dto.create.CreateAuthorRequest;
import aui.lab.sobanski.book.database.book.dto.get.all.GetAuthorsResponse;
import aui.lab.sobanski.book.database.book.dto.get.one.GetAuthorResponse;
import aui.lab.sobanski.book.database.book.dto.update.UpdateAuthorRequest;
import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private final static String DATE_CANNOT_BE_PARSED_FORMAT = "date: text '%s' cannot be parsed";

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetAuthorsResponse> getAuthors() {
        return ResponseEntity.ok(GetAuthorsResponse.entityToDtoMapper().apply(authorService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable("id") long id) {
        return authorService.find(id)
                .map(value -> ResponseEntity.ok(GetAuthorResponse.entityToDtoMapper(authorService.getStringFunction()).apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder) {
        try {
            Author author = CreateAuthorRequest
                    .dtoToEntityMapper(authorService.getLocalDateFunction())
                    .apply(request);
            authorService.create(author);
            return ResponseEntity.created(builder.pathSegment("api", "authors", "{id}")
                    .buildAndExpand(author.getId()).toUri()).build();
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    String.format(DATE_CANNOT_BE_PARSED_FORMAT, request.getBirthDate())
            );
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateAuthor(@RequestBody UpdateAuthorRequest request, @PathVariable("id") long id) {
        try {
            Optional<Author> author = authorService.find(id);
            if (author.isPresent()) {
                UpdateAuthorRequest.dtoToEntityUpdater(authorService.getLocalDateFunction()).apply(author.get(), request);
                authorService.update(author.get());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    String.format(DATE_CANNOT_BE_PARSED_FORMAT, request.getBirthDate())
            );
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") long id) {
        Optional<Author> author = authorService.find(id);
        if (author.isPresent()) {
            authorService.delete(author.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
