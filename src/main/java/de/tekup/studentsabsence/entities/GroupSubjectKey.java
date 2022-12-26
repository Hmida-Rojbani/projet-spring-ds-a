package de.tekup.studentsabsence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSubjectKey implements Serializable {
    public GroupSubjectKey(Long id, Object id2) {
		// TODO Auto-generated constructor stub
	}
	@Column(name = "group_id")
    private Long groupId ;
    @Column(name = "subject_id")
    private Long subjectId;
}
