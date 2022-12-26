package de.tekup.studentsabsence.holders;

import de.tekup.studentsabsence.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupSubjectHolder {
    @NotNull(message = "Subject is required")
    private Subject subject;
    @NotNull(message = "Hours is required")
    @Positive(message = "Should be positive")
    private float hours;
	public Subject getSubject() {
		// TODO Auto-generated method stub
		return null;
	}
	public float getHours() {
		// TODO Auto-generated method stub
		return 0;
	}
}
