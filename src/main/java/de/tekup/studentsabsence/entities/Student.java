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
    //@Column(unique=true)
    private Long sid;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @ManyToOne
    //@MapsId("group_id")
    @JoinColumn(name = "group_id")
    private Group group;
    @OneToMany(mappedBy = "student")
    private List<Absence> absences;



}
