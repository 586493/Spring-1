package aui.lab.sobanski.book.database.book.controller;

import aui.lab.sobanski.book.database.book.dto.create.CreateAuthorRequest;
import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder) {
        try {
            Author author = CreateAuthorRequest.dtoToEntityMapper().apply(request);
            author = authorService.create(author);
            return ResponseEntity.created(builder.pathSegment("api", "authors", "{id}")
                    .buildAndExpand(author.getId()).toUri()).build();
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
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
