package aui.lab.sobanski.book.database.book.event.repository;

import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.event.dto.CreateAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AuthorEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public AuthorEventRepository(@Value("${books.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Long authorId) {
        restTemplate.delete("/authors/{id}", authorId);
    }

    public void create(Author author) {
        restTemplate.postForLocation("/authors",
                CreateAuthorRequest.entityToDtoMapper().apply(author));
    }
}
