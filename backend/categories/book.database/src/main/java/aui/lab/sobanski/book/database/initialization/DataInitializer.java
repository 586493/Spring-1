package aui.lab.sobanski.book.database.initialization;


import aui.lab.sobanski.book.database.book.entity.Category;
import aui.lab.sobanski.book.database.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final CategoryService categoryService;

    @Autowired
    public DataInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    private synchronized void init() {
        Category category1 = Category.builder()
                .name("fantasy")
                .description("Fantasy is a genre of speculative fiction " +
                        "set in a fictional universe, often inspired by real world myth " +
                        "and folklore. Its roots are in oral traditions, which then became " +
                        "fantasy literature and drama.")
                .build();
        categoryService.create(category1);

        Category category2 = Category.builder()
                .name("science fiction")
                .description("Science fiction is a genre of speculative fiction " +
                        "that typically deals with imaginative and futuristic concepts " +
                        "such as advanced science and technology, space exploration, " +
                        "time travel, parallel universes, and extraterrestrial life. ")
                .build();
        categoryService.create(category2);

        Category category3 = Category.builder()
                .name("historical novel")
                .description("Historical novel is a novel having as its setting a period of " +
                        "history and usually introducing some historical personages and events")
                .build();
        categoryService.create(category3);
    }

}
