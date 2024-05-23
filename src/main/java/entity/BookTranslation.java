package entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "book_translations")
public class BookTranslation extends Book {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "translate_name", columnDefinition = "varchar(255) default 'unknown'")
	private String translateName;
	private String language;
	
	@Override
	public String toString() {
		return "BookTranslation ["+ super.toString()+"translateName=" + translateName + ", language=" + language + "]";
	}
	
	
	
	
}
