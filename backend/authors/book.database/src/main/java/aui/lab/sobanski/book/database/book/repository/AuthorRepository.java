package aui.lab.sobanski.book.database.book.repository;

import aui.lab.sobanski.book.database.book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
