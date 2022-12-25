package de.tekup.studentsabsence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Absence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Start date is required")
    @Past(message = "Should be a date in the past")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;
    @NotNull(message = "Hours is required")
    @Positive(message = "Should be positive")
    private float hours;
   //TODO Complete Relations with other entities
   @ManyToOne
   @JoinColumn(name = "student_sid")
   private Student student;


    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
