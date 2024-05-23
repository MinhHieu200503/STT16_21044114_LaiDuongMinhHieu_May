package dao;

import java.util.List;
import java.util.Map;

import entity.Book;

public interface Interface_Example {
	public List<Book> listRatedBooks(String author, int rating);
	public Map<String, Long> countBooksByAuthor();
	public boolean updateReviews(String isbn, String readerID, int rating, String comment);
}
