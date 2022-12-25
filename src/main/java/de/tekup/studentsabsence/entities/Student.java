package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"image","group","absences"})
public class Student implements Serializable {
    //TODO Complete Validations of fields
    /*DONE*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;
    @NotBlank(message="Phone number is required")
    @Pattern(regexp = "^[0-9]{8}$", message = "Phone number must contain  8 numbers")
    private String phone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    //TODO Complete Relations with other entities
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "student")
    private List<Absence> absenceList;
}
