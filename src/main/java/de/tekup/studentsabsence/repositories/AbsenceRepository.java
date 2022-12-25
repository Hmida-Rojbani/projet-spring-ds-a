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

    @Query(value = "SELECT subject_id FROM(SELECT groupe.id as grp,absence.subject_id,SUM(absence.hours)as nbh from absence ,groupe ,student\n" +
            "            WHERE student.sid=absence.student_id and groupe.id=student.group_id  AND groupe.id=?1 \n" +
            "            GROUP BY absence.subject_id)as h WHERE nbh=(SELECT Max(nbh) From(SELECT groupe.id as grp,absence.subject_id,SUM(absence.hours)as nbh from absence ,groupe ,student  WHERE student.sid=absence.student_id and" +
            " groupe.id=student.group_id AND groupe.id=?1 GROUP BY absence.subject_id)as h  ) LIMIT 1",nativeQuery = true)
    Long findSumHoursBySubject(Long gid);
    /* Find */
    @Query(value = "SELECT subject_id FROM(SELECT groupe.id as grp,absence.subject_id,SUM(absence.hours)as nbh from absence ,groupe ,student\n" +
            "            WHERE student.sid=absence.student_id and groupe.id=student.group_id  AND groupe.id=?1 \n" +
            "            GROUP BY absence.subject_id)as h WHERE nbh=(SELECT MIN(nbh) From(SELECT groupe.id as grp,absence.subject_id,SUM(absence.hours)as nbh from absence ,groupe ,student  WHERE student.sid=absence.student_id and" +
            " groupe.id=student.group_id AND groupe.id=?1 GROUP BY absence.subject_id)as h  )LIMIT 1 ",nativeQuery = true)
    Long findSumMinHoursBySubject(Long gid);
}
