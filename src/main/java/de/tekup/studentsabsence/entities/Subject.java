package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    // relation one to one (subject ,absence )
    @OneToOne(mappedBy = "subject")
    private Absence absence;
    // relation one to many (Subject ,GroupSubject )
    @OneToMany(mappedBy = "subject")
   private List<GroupSubject> groupSubjects;

}
