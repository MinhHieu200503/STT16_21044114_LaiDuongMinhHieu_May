package main;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.Interface_Example;
import dao_Implement.Implement_Example;
import entity.Book;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class Test_Example {
	public EntityManagerFactory emf;
	public Interface_Example ie;

	@Before
	 void init() throws Exception {
		emf =  Persistence.createEntityManagerFactory("BookServer");
		ie = new Implement_Example(emf);
		
	}

	@After
	 void teardown() throws Exception {
		emf.close();
	}

	@Test
	void testListRatedBooksFound() {
		List<Book> books = ie.listRatedBooks("Robert C. Martin", 4);
		assert books.size() > 0;
	}
	
	@Test
	void testListRatedBooksNotFound() {
		List<Book> books = ie.listRatedBooks("Robert C. Martisn", 5);
		assert books.size() == 0;
	}
	
	@Test
	void testCountBooksByAuthorFound() {
		assert ie.countBooksByAuthor().size() > 0;
	}

	@Test
	void testCountBooksByAuthorSortByKey() {
		assert ie.countBooksByAuthor().keySet().stream().sorted().findFirst().get().equals("Andrew Hunt");
	}
	
	@Test
	void testUpdateReviews() {
		assert ie.updateReviews("888-0132350800", "11", 2, "Great booasdasjdklsk!") == true;
		assert ie.updateReviews("888-0132350800", "12", 2, "Great booasdasjdklsk!") == false;
		assert ie.updateReviews("888-0132350800", "11", 2, "") == false;
		assert ie.updateReviews("888-0132350800", "11", -1, "Great booasdasjdklsk!") == false;
	}
	
	

}
