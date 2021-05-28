package dummy.repo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dummy.entity.Book;

@Repository
@Transactional
public interface MyjdbcRepository extends CrudRepository<Book, Integer> {	
	
	@Modifying
	@Query("update book b set b.author =:author, b.title =:title where b.isbn =:isbn")
	void updateBook(@Param("author") String author,@Param("title") String title, @Param("isbn") int isbn);
	
}
