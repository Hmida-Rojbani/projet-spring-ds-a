package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.entities.GroupSubject;
import de.tekup.studentsabsence.entities.GroupSubjectKey;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupSubjectRepository extends CrudRepository<GroupSubject, GroupSubjectKey> {
    List<GroupSubject> findAllByGroup(Group id);
    
    @Query(value=" select * from group_subject gs ,subject s, group g ,absence a where ((gs.subject_id=s.id) and (gs.group_id=g.id) and (a.subject_id=s.id) and (g.id =:id) and (avg(a.hours)>= select subject,max(hours) from absence where g.id =: id))",nativeQuery = true)
    List<GroupSubject> findAlltauxEleve(Long id);
    @Query(value=" select * from group_subject gs ,subject s, group g ,absence a where ((gs.subject_id=s.id) and (gs.group_id=g.id) and (a.subject_id=s.id) and (g.id =:id) and (avg(a.hours)<= select subject,min(hours) from absence where g.id =: id))",nativeQuery = true)
    List<GroupSubject> findAlltauxbas(Long id);
	void findAllByGroup_idAndSubject_Id(Long gid, Long sid);
}
