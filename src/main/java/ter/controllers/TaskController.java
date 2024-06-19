package ter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ter.services.TaskService;

@Controller
public class TaskController {
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
		
	@GetMapping("/tasks")
	public String getAllTasks(Model model) {
		model.addAttribute("tasks", taskService.getAllTasksFromFirstTable());
		model.addAttribute("tasks2", taskService.getAllTasksFromSecondTable());
		return "tasks.html";
	}
}
