package ter.services;

import org.springframework.stereotype.Service;

import ter.entities.Task2Entity;
import ter.entities.TaskEntity;
import ter.repositories.Task2Repository;
import ter.repositories.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final Task2Repository task2repo;
    
    public TaskService(TaskRepository taskRepo, Task2Repository task2repo) {
        this.taskRepo = taskRepo;
        this.task2repo = task2repo;
    }

    public Iterable<TaskEntity> getAllTasksFromFirstTable() {
        return taskRepo.findAll();
    }
    public Iterable<Task2Entity> getAllTasksFromSecondTable() {
        return task2repo.findAll();
    }
}
