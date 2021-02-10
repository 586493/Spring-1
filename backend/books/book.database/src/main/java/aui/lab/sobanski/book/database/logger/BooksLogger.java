package aui.lab.sobanski.book.database.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BooksLogger {

    private final static Logger LOGGER = LoggerFactory.getLogger(BooksLogger.class);

    public static void logCategoryDelete(long id) {
        LOGGER.info("BooksLogger - delete category {}", id);
    }

    public static void logCategoryCreate(long id) {
        LOGGER.info("BooksLogger - create category {}", id);
    }

    public static void logAuthorDelete(long id) {
        LOGGER.info("BooksLogger - delete author {}", id);
    }

    public static void logAuthorCreate(long id) {
        LOGGER.info("BooksLogger - create author {}", id);
    }

    public static void logBookDelete(long id) {
        LOGGER.info("BooksLogger - delete book {}", id);
    }

    public static void logBookCreate(long id) {
        LOGGER.info("BooksLogger - create book {}", id);
    }
}
