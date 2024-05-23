package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.Book;

public class Client {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 7878);
				Scanner sc = new Scanner(System.in);) {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			int choice = 0;
			
			
			while (true) {
				System.out.println("Enter your choice: \n" + "1. Danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên tác giả và\r\n"
						+ "có điểm đánh giá từ mấy sao trở lên\n"
						+ "2. Get the number of students enrolled in a course\n"
						+ "3. Get list of onsite coursesasd\n");
				choice = sc.nextInt();
				out.writeInt(choice);
				out.flush();
				switch (choice) {
					case 1: {
						System.out.println("Enter the author name: ");
						sc.nextLine();
						String author = sc.nextLine();
						System.out.println("Enter the rating: ");
						int rating = sc.nextInt();
						out.writeUTF(author);
						out.writeInt(rating);
						out.flush();
						List<Book> result = (List<Book>) in.readObject();
						if (result.isEmpty()) {
							System.out.println("No books found");
						} else {
							for (Book book : result) {
								System.out.println(book);
							}
						}
					}
					case 2:{
						Map<String, Long> result = (Map<String, Long>) in.readObject();
						System.out.println("Result: ");
						result.forEach((k, v) -> System.out.println(k + ": " + v));
					}
					case 3:{
						sc.nextLine();
						System.out.println("Enter the isbn: ");
						String isbn = sc.nextLine();
						System.out.println("Enter the personID: ");
						String personID = sc.nextLine();
						System.out.println("Enter the rating: ");
						int rating = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the comment: ");
						String comment = sc.nextLine();
						out.writeUTF(isbn);
						out.writeUTF(personID);
						out.writeInt(rating);
						out.writeUTF(comment);
						out.flush();						
						boolean result = (boolean) in.readObject();
						if (result) {
							System.out.println("Review updated");
						} else {
							System.out.println("Review not updated");
						}
						
					}
				}
			}
			
           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
