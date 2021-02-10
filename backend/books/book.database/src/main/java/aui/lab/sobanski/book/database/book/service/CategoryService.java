package aui.lab.sobanski.book.database.book.service;

import aui.lab.sobanski.book.database.book.entity.Category;
import aui.lab.sobanski.book.database.book.repository.CategoryRepository;
import aui.lab.sobanski.book.database.logger.BooksLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Optional<Category> find(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        BooksLogger.logCategoryDelete(id);
    }

    @Transactional
    public Category create(Category category) {
        BooksLogger.logCategoryCreate(category.getId());
        return repository.save(category);
    }

}
