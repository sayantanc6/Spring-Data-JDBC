package dummy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Book")
public class Book {

	private String author;

	@Id
	private int isbn;
	private String title;
	private double price;

	
	public Book(String author, int isbn, String title, double price) {
		super();
		this.author = author;
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", isbn=" + isbn + ", title=" + title + ", price=" + price + "]";
	}

}
