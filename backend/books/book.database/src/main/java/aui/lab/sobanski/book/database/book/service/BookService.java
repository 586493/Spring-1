package aui.lab.sobanski.book.database.book.service;

import aui.lab.sobanski.book.database.book.entity.Book;
import aui.lab.sobanski.book.database.book.repository.BookRepository;
import aui.lab.sobanski.book.database.logger.BooksLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> find(Long id) {
        return repository.findById(id);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(Book book) {
        repository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        BooksLogger.logBookDelete(id);
    }

    @Transactional
    public void create(Book book) {
        repository.save(book);
        BooksLogger.logBookCreate(book.getId());
    }

}
