package ter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ter.entities.Task2Entity;

@Repository
public interface Task2Repository
	extends CrudRepository<Task2Entity, Integer>{
}
