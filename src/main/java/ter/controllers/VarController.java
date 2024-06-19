package ter.controllers;

import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ter.entities.Task2Entity;
import ter.entities.TaskEntity;
import ter.services.Solutions;
import ter.services.TaskService;

@Controller
public class VarController {
	private final TaskService taskService;
	// private final Logger log = Logger.getLogger(VarController.class.getName());

	public VarController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/var")
	public String VarPage(HttpSession session, @RequestParam int option, Model model) {
		session.setAttribute("option", option);
		model.addAttribute("option", option);
		if (option == 1) {
			model.addAttribute("tasks", taskService.getAllTasksFromFirstTable());
		} else {
			model.addAttribute("tasks", taskService.getAllTasksFromSecondTable());
		}

		return "var.html";
	}

	@PostMapping("/var")
	public String MakeVar(HttpServletRequest request, @SessionAttribute("option") Integer option,
			@RequestParam(name = "taskIds", required = false) String[] taskIds,
			@RequestParam(name="numPages") int numPages,
			Model model) throws Exception {

		if (taskIds != null) {
			ProcessingTasks(taskIds, option, numPages);
		}
		return "redirect:/";
	}

	private void ProcessingTasks(String[] tasksIds, int option, int numPages) throws Exception {
		List<Integer> chosenTasks = new ArrayList<>();
		for (String Stringid : tasksIds) {
			chosenTasks.add(Integer.valueOf(Stringid));
		}

		List<TaskEntity> task1 = new ArrayList<>();
		List<Task2Entity> task2 = new ArrayList<>();
		taskService.getAllTasksFromFirstTable().iterator().forEachRemaining(task1::add);
		taskService.getAllTasksFromSecondTable().iterator().forEachRemaining(task2::add);
		List<List<Double>> params = new ArrayList<>();

		if (option == 1) {
			for (var task : task1) {
				params.add(task.getParams());

			}
		} else {
			for (var task : task2) {
				params.add(task.getParams());
			}
		}
		
		Solutions.mainFunc(chosenTasks, params, option, numPages);
	}
}