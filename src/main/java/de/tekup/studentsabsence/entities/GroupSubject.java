package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSubject implements Serializable {
    public GroupSubject(GroupSubjectKey groupSubjectKey, Group group2, Subject subject2, float hours2) {
		// TODO Auto-generated constructor stub
	}

	@EmbeddedId
    private GroupSubjectKey id;

    @ManyToOne
    @MapsId("group_id")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("subject_id")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private float hours;
}
