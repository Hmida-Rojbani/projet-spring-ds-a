package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"image","group","absences"})
public class Student implements Serializable {

    //TODO Complete Validations of fields   OK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    @NotBlank(message = "firstName is required")
    @Size(max = 50, min = 3)
    private String firstName;
    @NotBlank(message = "LastName is required")
    @Size(max = 50, min = 3)
    private String lastName;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    @Pattern(regexp = "^[0-9]{8}$", message = "phone not valid")
    @Column(unique = true)
    private String phone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    //TODO Complete Relations with other entities OK
    //Mariem Jaziri & Raja Ben Salem

    @OneToMany
    List<Absence> absences;
    @ManyToOne
    private Group group;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", absences=" + absences +
                ", group=" + group +
                ", image=" + image +
                '}';
    }
}
