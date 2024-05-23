package main;

import dao.Interface_Example;
import dao_Implement.Implement_Example;
import entity.ReviewID;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("BookServer");
		
		Interface_Example ie = new Implement_Example(emf);
		
//		ie.listRatedBooks("Robert C. Martin", 4).forEach(System.out::println);
		ie.countBooksByAuthor().forEach((k, v) -> System.out.println(k + ": " + v));
//		ReviewID rid = new ReviewID("888-0132350800", "11");
//		
//		ie.updateReviews(rid.getISBN(), rid.getPersonID(), 2, "Great booasdasjdklsk!");
	}
}
