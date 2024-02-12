package ma.pc.Procheck.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidature {

	@Id
	@GeneratedValue(generator="my_seq")
	@SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
	private Long id;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="mail")
	private String email;
	
	@Column(name="phoneNumber")
	private Long phoneNumber;
	
	@Column(name="studyLevel")
	private String studyLevel;
	
	@Column(name="NbrYearsExp")
	private Long NbrYearsExp;
	
	@Column(name="lastEmployer")
	private String lastEmployer;
	
	@Column(name="cvName")
	private String cvName;
}
