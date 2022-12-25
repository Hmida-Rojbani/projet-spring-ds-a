package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupSubjectRepository extends CrudRepository<GroupSubject, GroupSubjectKey> {
    List<GroupSubject> findAllByGroup(Group id);

    List<GroupSubject> findAllBySubject(Subject sid);

    List<GroupSubject> findGroupSubjectsByGroupAndAndSubject (Long gid, Long sid);
}
