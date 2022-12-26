package de.tekup.studentsabsence.services;

import de.tekup.studentsabsence.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentBySid(Long sid);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    Optional<Student> deleteStudent(Long sid);
}
