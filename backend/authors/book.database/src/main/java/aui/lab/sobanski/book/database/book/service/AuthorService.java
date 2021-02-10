package aui.lab.sobanski.book.database.book.service;

import aui.lab.sobanski.book.database.book.entity.Author;
import aui.lab.sobanski.book.database.book.event.repository.AuthorEventRepository;
import aui.lab.sobanski.book.database.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AuthorService {

    private AuthorEventRepository eventRepository;
    private AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository, AuthorEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Author> find(Long id) {
        return repository.findById(id);
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(Author author) {
        repository.save(author);
    }

    @Transactional
    public void delete(Long id) {
        eventRepository.delete(id);
        repository.deleteById(id);
    }

    @Transactional
    public void create(Author author) {
        repository.save(author);
        eventRepository.create(author);
    }

    public Function<String, LocalDate> getLocalDateFunction() {
        return LocalDate::parse;
    }

    public Function<LocalDate, String> getStringFunction() {
        return LocalDate::toString;
    }
}
