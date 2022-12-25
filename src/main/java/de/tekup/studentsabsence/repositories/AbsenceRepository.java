package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Absence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbsenceRepository extends CrudRepository<Absence, Long> {
    List<Absence> findAllByStudent(Long id);
    List<Absence> findAllByStudentSid(Long sid);
    List<Absence> findAllByStudentSidAndSubjectId(Long sid, Long id);
    List<Absence> findAllByStudentAndSubjectId(Long gid, Long id);
}
