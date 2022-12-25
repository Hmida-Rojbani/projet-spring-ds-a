package de.tekup.studentsabsence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    //@OneToMany
   // private List<GroupSubject> groupSubjects = new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    Set<Group> groups;

    // relation one to one entre sujet et absence
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "abcence_id", referencedColumnName = "id")
    private  Absence absence;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Absence getAbsence() {
        return absence;
    }
}
