package de.tekup.studentsabsence.entities;

import de.tekup.studentsabsence.enums.LevelEnum;
import de.tekup.studentsabsence.enums.SpecialityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity(name="group")
@Data
@ToString(exclude = "students")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Label is required")
    private String label;
    @Enumerated(EnumType.STRING)
    private LevelEnum level;
    @NotNull(message = "Speciality is required")
    @Enumerated(EnumType.STRING)
    private SpecialityEnum speciality;
 
    @OneToMany
    private List<Student> student;


}
