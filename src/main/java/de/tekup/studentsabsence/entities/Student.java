package de.tekup.studentsabsence.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.engine.internal.AbstractEntityEntry;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"image","group","absences"})
public class Student implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Id
    private Long sid;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @OneToMany(mappedBy = "student")
    List<Absence> absences;

    @OneToOne
    private Image image;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group  group;


}
