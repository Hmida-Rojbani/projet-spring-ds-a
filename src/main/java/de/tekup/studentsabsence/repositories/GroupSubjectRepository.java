package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.entities.GroupSubject;
import de.tekup.studentsabsence.entities.GroupSubjectKey;
import de.tekup.studentsabsence.entities.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupSubjectRepository extends CrudRepository<GroupSubject, GroupSubjectKey> {
    List<GroupSubject> findAllByGroup(Group id);
    //List<GroupSubject> findAllByGroupAndSubject(Long id, Long sid);
    GroupSubject findGsByGroupAndSubject(Long gid, Long sid);

    ///TODO create a methode to find a groupSubject by Group Id and Subject Id OK
    //Mariem Jaziri & Raja Ben Salem
}
