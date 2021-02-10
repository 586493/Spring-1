package aui.lab.sobanski.book.database.book.service;

import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.repository.AuthorRepository;
import aui.lab.sobanski.book.database.logger.BooksLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Optional<Author> find(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        BooksLogger.logAuthorDelete(id);
    }

    @Transactional
    public Author create(Author author) {
        BooksLogger.logAuthorCreate(author.getId());
        return repository.save(author);
    }

}
