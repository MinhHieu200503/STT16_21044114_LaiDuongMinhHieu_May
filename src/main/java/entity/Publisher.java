package entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "publishers")

public class Publisher implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "publisher_id")
	private String id;
	private String address;
	private String email;
	private String phone;
	private String name;
	
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	private Set<Book> books;
	
	@Override
	public String toString() {
		return "Publisher [id=" + id + ", address=" + address + ", email=" + email + ", phone=" + phone + ", name="
				+ name + "]";
	}
}
