package dummy.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dummy.entity.Book;

@Repository
@Transactional
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
	
	@Query("select b.* from book b "
		+ " join book_author ba on ba.book = b.isbn"
		+ " where ba.author = :authorID")
	List<Book> findByAuthorID(@Param("authorID") int author);

	@Modifying
	@Query("update book b set b.author =:author, b.title =:title where b.isbn =:isbn")
	void updateBook(@Param("author") String author,@Param("title") String title, @Param("isbn") int isbn);
	
}
