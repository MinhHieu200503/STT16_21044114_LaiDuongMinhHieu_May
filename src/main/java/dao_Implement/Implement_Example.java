package dao_Implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Implement_Example implements dao.Interface_Example {
	private EntityManagerFactory entityManagerFactory;
	
	public Implement_Example(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<Book> listRatedBooks(String author, int rating) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Book> books = new ArrayList<>();
		try {
			books = entityManager
					.createQuery(
							"SELECT b FROM Book b join Review r on b.ISBN = r.book.ISBN JOIN FETCH b.authors ba WHERE ba like :author AND r.rating >= :rating"
							, Book.class)
					.setParameter("author", author).setParameter("rating", rating).getResultList();
			entityManager.close();
			return books;
		}
		catch (Exception e) {
			entityManager.close();
			throw e;
		}
	}

	@Override
	public Map<String, Long> countBooksByAuthor() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT COUNT(b.name), a.author FROM books_authors a JOIN books b ON a.ISBN = b.ISBN GROUP BY a.author";
		String queryJPA = "SELECT a, COUNT(b)  FROM Book b JOIN b.authors a GROUP BY a ORDER BY COUNT(b) DESC";
		try {
			List<Object[]> results = entityManager
					.createQuery(queryJPA, Object[].class)
					.getResultList();
			entityManager.close();
			Map<String, Long> map = new java.util.HashMap<>();
			for (Object[] result : results) {
				map.put((String) result[0], (Long) result[1]);
			}
			
//			Sort by key
			map = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(java.util.stream.Collectors
					.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, java.util.LinkedHashMap::new));
			
			return map;
		}
		catch (Exception e) {
			entityManager.close();
			throw e;
		}
	}

	@Override
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
//		Validate
		if (rating < 1 || rating > 5) {
			System.out.println("Rating must be between 1 and 5");
			entityManager.close();
			return false;
		}
//		exists
		long count = (long) entityManager
				.createQuery("SELECT COUNT(r) FROM Review r WHERE r.id.ISBN = :isbn AND r.id.personID = :person_id")
				.setParameter("isbn", isbn).setParameter("person_id", readerID).getSingleResult();
		if (count == 0) {
			System.out.println("Review not found");
			entityManager.close();
			return false;
		}
//		comment
		if (comment == null || comment.isBlank()) {
			System.out.println("Comment cannot be empty");
			entityManager.close();
			return false;
		}
		
		try {
			entityManager.getTransaction().begin();
			int updated = entityManager.createQuery(
					"UPDATE Review r SET r.rating = :rating, r.comment = :comment WHERE r.id.ISBN = :isbn AND r.id.personID = :person_id")
					.setParameter("rating", rating).setParameter("comment", comment).setParameter("isbn", isbn)
					.setParameter("person_id", readerID).executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.close();
			if (updated == 0) {
				System.out.println("Review not found");
				return false;
			}
			
			if (updated >= 1) {
				System.out.println("Updated successfully");
				
			}

		} catch (Exception e) {
			entityManager.close();
			throw e;
		}
		return true;

	}
	
	
	
	
}
