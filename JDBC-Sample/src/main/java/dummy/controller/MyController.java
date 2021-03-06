package dummy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dummy.entity.Author;
import dummy.entity.Book;
import dummy.repo.AuthorRepository;
import dummy.repo.BookRepository;
import dummy.repo.MyBookDao;


@RestController
public class MyController {
	
	@Autowired
	AuthorRepository authorrepo;
	
	@Autowired
	BookRepository repo;
	
	List<Book> booklist;
	
	@Autowired
	MyBookDao dao;
	
	@GetMapping(value = "/book/{isbn}",produces = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public Book book(@PathVariable("isbn") int isbn) {
		return repo.findById(isbn).get();
	}
	
	@PostMapping(value = "/addBook",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public  Book addBook(@RequestBody Book book) {		 
		return repo.save(book);
	}	
	
	@PutMapping(value = "/updatebook",
			headers = "Accept=application/json")
	public  void updatebook(@RequestParam("title")String title,@RequestParam("author")String author, @RequestParam("isbn") int isbn) {
		System.out.println("title:"+title);
		System.out.println("author:"+author);
		System.out.println("isbn:"+isbn); 
	    repo.updateBook(author, title, isbn); 
	}
	
	@DeleteMapping(value = "/deletebook",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public  void deleteCust(@RequestParam("isbn") int isbn) {
		repo.deleteById(isbn);
	}
	
	@GetMapping(value = "/batchinsert",
			produces = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public int[] batchinsert() {
		booklist = new ArrayList<Book>();
		booklist.add(new Book("author1",103,"title1",50.05,null));
		booklist.add(new Book("author2",104,"title2",50.10,null));
		booklist.add(new Book("author3",105,"title3",50.15,null));
		booklist.add(new Book("author4",106,"title4",50.20,null));
		 return dao.batchInsert(booklist);
	}

	@GetMapping(value = "/batchupdate",
			produces = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public Iterable<Book> batchUpdate() {
		booklist = new ArrayList<Book>();
		booklist.add(new Book("author",103,"title",50.05,null));
		booklist.add(new Book("author",104,"title",50.10,null));
		booklist.add(new Book("author",105,"title",50.15,null));
		booklist.add(new Book("author",106,"title",50.20,null));
		return repo.saveAll(booklist);
	}

	@GetMapping(value = "/batchdelete",
			produces = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public void batchdelete() {
		booklist = new ArrayList<Book>();
		booklist.add(new Book("author",103,"title",50.05,null));
		booklist.add(new Book("author",104,"title",50.10,null));
		booklist.add(new Book("author",105,"title",50.15,null));
		booklist.add(new Book("author",106,"title",50.20,null));
		repo.deleteAll(booklist);
	}
	
	@GetMapping(value = "/book",
			produces = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public List<Book> findBooksauthorID(@RequestParam("authorID") int authorID) {
		return repo.findByAuthorID(authorID);
	}
	
	// https://stackoverflow.com/questions/35992342/how-to-send-special-characters-in-request-parameters-in-spring
	@GetMapping(value = "/author",
			produces = MediaType.APPLICATION_JSON_VALUE,
			headers = "Accept=application/json")
	public List<Author> findauthors(@RequestParam("exp") String exp) {
		return authorrepo.findByFullnameLike(exp);
	}
}
