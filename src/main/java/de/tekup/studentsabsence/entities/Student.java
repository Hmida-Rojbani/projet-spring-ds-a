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
    //TODO Complete Validations of fields


    @Id
    private Long sid;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Pattern(regexp = "^[0-9]{8}$", message = "Phone not valid")
    private String phone;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @Past
    private LocalDate dob;

    //TODO Complete Relations with other entities
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @OneToMany(mappedBy = "student")
    private List<Absence> absences;
    @OneToOne(mappedBy = "student")
    private Image image;



}
