package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import dao.Interface_Example;
import dao_Implement.Implement_Example;
import entity.Book;
import jakarta.persistence.Persistence;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(7878);) {
			System.out.println("Server started on port 7878");
			while (true) {
				Socket client = server.accept();
				System.out.println("Client connected");
				System.out.println("Client IP: " + client.getInetAddress().getHostName());
				System.out.println("Client Port: " + client.getPort());
				Thread t = new Thread(new ClientHandle(client));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientHandle implements Runnable {
	private Socket socket;
	private Interface_Example example;
	public ClientHandle(Socket client) {
		super();
		this.socket = client;
		this.example = new Implement_Example(Persistence.createEntityManagerFactory("BookServer"));
	}
    @Override
    public void run() {
    	try {
    		DataInputStream dis = new DataInputStream(socket.getInputStream());
    		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    		int choice = 0;
    		
    		while (true) {
    			choice = dis.readInt();
    			switch (choice) {
					case 1:{
						
						String author = dis.readUTF();
						int rating = dis.readInt();
						List<Book> result = example.listRatedBooks(author, rating);
						oos.flush();
						oos.writeObject(result);
						break;
					}
	    			
	    			case 2: {
	    				Map<String, Long> result = example.countBooksByAuthor();
						oos.flush();
	    				oos.writeObject(result);
	    				break;
	    			}
	    			case 3:{
						String isbn = dis.readUTF();
						String person = dis.readUTF();
						int rating = dis.readInt();
						String comment = dis.readUTF();
						boolean result = example.updateReviews(isbn, person, rating, comment);
						oos.flush();
						System.out.println(result);
						oos.writeObject(result);
						break;
	    			}
    			}
    		}
    	}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
}
