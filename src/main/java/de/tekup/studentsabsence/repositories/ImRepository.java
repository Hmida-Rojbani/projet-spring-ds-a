package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImRepository extends CrudRepository<Image, String> {
}
