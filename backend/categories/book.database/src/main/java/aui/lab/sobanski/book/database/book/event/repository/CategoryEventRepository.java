package aui.lab.sobanski.book.database.book.event.repository;

import aui.lab.sobanski.book.database.book.entity.Category;
import aui.lab.sobanski.book.database.book.event.dto.CreateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CategoryEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public CategoryEventRepository(@Value("${books.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Long categoryId) {
        restTemplate.delete("/categories/{id}", categoryId);
    }

    public void create(Category category) {
        restTemplate.postForLocation("/categories",
                CreateCategoryRequest.entityToDtoMapper().apply(category));
    }
}
