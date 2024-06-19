package ter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ter.entities.TaskEntity;

@Repository
public interface TaskRepository
	extends CrudRepository<TaskEntity, Integer>{
}
