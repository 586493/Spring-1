package aui.lab.sobanski.book.database.book.controller;

import aui.lab.sobanski.book.database.book.dto.create.CreateCategoryRequest;
import aui.lab.sobanski.book.database.book.dto.get.all.GetCategoriesResponse;
import aui.lab.sobanski.book.database.book.dto.get.one.GetCategoryResponse;
import aui.lab.sobanski.book.database.book.dto.update.UpdateCategoryRequest;
import aui.lab.sobanski.book.database.book.entity.Category;
import aui.lab.sobanski.book.database.book.service.CategoryService;
import aui.lab.sobanski.book.database.book.service.exceptions.NotUniqueValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<GetCategoriesResponse> getCategories() {
        return ResponseEntity.ok(GetCategoriesResponse.entityToDtoMapper().apply(categoryService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable("id") long id) {
        return categoryService.find(id)
                .map(value -> ResponseEntity.ok(GetCategoryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequest request, UriComponentsBuilder builder) {
        try {
            Category category = CreateCategoryRequest.dtoToEntityMapper().apply(request);
            categoryService.create(category);
            return ResponseEntity.created(builder.pathSegment("api", "categories", "{id}")
                    .buildAndExpand(category.getId()).toUri()).build();
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotUniqueValueException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryRequest request, @PathVariable("id") long id) {
        try {
            Optional<Category> category = categoryService.find(id);
            if (category.isPresent()) {
                categoryService.update(UpdateCategoryRequest.dtoToEntityUpdater().apply(category.get(), request));
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotUniqueValueException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") long id) {
        Optional<Category> category = categoryService.find(id);
        if (category.isPresent()) {
            categoryService.delete(category.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
