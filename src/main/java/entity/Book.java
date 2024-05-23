package entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType)
public class Book implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String ISBN;
	@Column(columnDefinition = "varchar(255) default 'unknown'")
	private String name;
	@Column(name = "publish_year")
	private int publishYear;
	@Column(name = "num_of_pages")
	private int numOfPages;
	private double price;
	
    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author")
    private Set<String> authors;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id")
	private entity.Publisher publisher;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private Set<Review> reviews;

    @Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", name=" + name + ", publishYear=" + publishYear + ", numOfPages=" + numOfPages
				+ ", price=" + price + ", authors=" + authors + "]";
	}
	
	
}
