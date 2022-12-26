package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "image", "group", "absences" })
public class Student implements Serializable {
	// TODO Complete Validations of fields
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sid;

	@NotBlank(message = "FirstName is required")
	private String firstName;

	@NotBlank(message = "LastName is required")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email be a valid")
	private String email;

	@NotBlank(message = "Phone is required")
	private String phone;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;

	// TODO Complete Relations with other entities

	@ManyToOne
	private Group group;

	@OneToOne
	private Image image;

	@OneToMany(mappedBy = "student")
	private List<Absence> absences;

}
