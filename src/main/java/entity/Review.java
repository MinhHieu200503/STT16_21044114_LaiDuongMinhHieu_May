package entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor


@Entity
@Table(name = "reviews")
public class Review implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ReviewID id;

	private int rating;
	private String comment;
	
	@ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
	@JoinColumn(name = "ISBN", insertable=false, updatable=false)
	private Book book;
	
	@ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
	@JoinColumn(name = "person_id", insertable=false, updatable=false)
	private Person person;
	
	
	@Override
	public String toString() {
		return "Reviews [id=" + id + ", rating=" + rating + ", comment=" + comment + "]";
	}
}
