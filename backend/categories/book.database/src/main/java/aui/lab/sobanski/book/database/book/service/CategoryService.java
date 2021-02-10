package aui.lab.sobanski.book.database.book.service;

import aui.lab.sobanski.book.database.book.entity.Category;
import aui.lab.sobanski.book.database.book.event.repository.CategoryEventRepository;
import aui.lab.sobanski.book.database.book.repository.CategoryRepository;
import aui.lab.sobanski.book.database.book.service.exceptions.NotUniqueValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategoryService {
    private final static String NAME_NOT_UNIQUE_FORMAT =
            "A category '%s' already exists. The category name must be unique.";
    private final static Function<Category, String> NOT_UNIQUE_MSG_FUNCTION =
            category -> String.format(NAME_NOT_UNIQUE_FORMAT, category.getName());

    private CategoryEventRepository eventRepository;
    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Category> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Category> find(String name) {
        return repository.findByName(name);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(Category category) {
        final Optional<Category> optCategory = find(category.getId());
        if (optCategory.isPresent()
                && optCategory.get().getName().equals(category.getName())
                && optCategory.get().getDescription().equals(category.getDescription())) {
            // same category name only if description has changed
            // otherwise throw NotUniqueValueException
            throw new NotUniqueValueException(NOT_UNIQUE_MSG_FUNCTION.apply(category));
        }
        repository.save(category);
    }

    @Transactional
    public void delete(Long id) {
        eventRepository.delete(id);
        repository.deleteById(id);
    }

    @Transactional
    public void create(Category category) {
        if (find(category.getName()).isPresent()) {
            throw new NotUniqueValueException(NOT_UNIQUE_MSG_FUNCTION.apply(category));
        }
        repository.save(category);
        eventRepository.create(category);
    }

}
