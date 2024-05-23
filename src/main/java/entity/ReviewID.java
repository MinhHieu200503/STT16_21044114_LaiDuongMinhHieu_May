package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Embeddable
public class ReviewID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3127649789194031055L;
	@Column(name = "ISBN")
	private String ISBN;
	@Column(name = "person_id")
	private String personID;
	
	
}
