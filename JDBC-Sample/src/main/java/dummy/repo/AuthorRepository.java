package dummy.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dummy.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

	List<Author> findByFullnameLike(String exp);
}
