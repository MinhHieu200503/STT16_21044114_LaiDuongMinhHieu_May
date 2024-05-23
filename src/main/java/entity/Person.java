package entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "people")
public class Person implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "person_id")
	private String id;
	@Column(name = "first_name", columnDefinition = "varchar(255) default 'unknown'")
	private String firstName;
	@Column(name = "last_name", columnDefinition = "varchar(255) default 'unknown'")
	private String lastName;
	private String email;
	@Column(name = "professional_title", columnDefinition = "varchar(255) default 'unknown'")
	private String professionTitle;
	
	@OneToMany(mappedBy = "person", fetch = jakarta.persistence.FetchType.LAZY)
	private Set<Review> reviews;
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", professionTitle=" + professionTitle + "]";
	}
	
}
