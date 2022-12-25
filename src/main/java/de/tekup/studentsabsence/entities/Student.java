package de.tekup.studentsabsence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"image","group","absences"})
public class Student implements Serializable {
    //TODO Complete Validations of fields


    @Id
    private Long sid;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @Email
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone is required")
    private String phone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Should be a date in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    //TODO Complete Relations with other entities
    @JsonBackReference
    @ManyToOne
    private Group group;

    @JsonBackReference
    @OneToMany(mappedBy = "student")
    private List<Absence> absences;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image ;



}
