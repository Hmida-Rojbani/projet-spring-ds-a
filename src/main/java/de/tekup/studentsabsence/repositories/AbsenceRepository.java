package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Absence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbsenceRepository extends CrudRepository<Absence, Long> {
    List<Absence> findAllByStudent_Group_Id(Long id);
    List<Absence> findAllByStudent_Sid(Long sid);
    List<Absence> findAllByStudent_SidAndSubject_Id(Long sid, Long id);
    List<Absence> findAllByStudent_Group_IdAndSubject_Id(Long gid, Long id);
    //Mariem Jaziri & Raja Ben Salem
    @Query("SELECT  s.name FROM Absence as a, Subject as s  where a.hours = (SELECT MAX(hours) FROM Absence ) AND s.id = a.id")
    List<Absence> findMaxAbsenceBySubject();

    @Query("SELECT  s.name FROM Absence as a, Subject as s  where a.hours = (SELECT MIN(hours) FROM Absence ) AND s.id = a.id")
    List<Absence> findMinAbsenceBySubject();
}
