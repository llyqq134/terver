package ter.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<InputStreamResource> MakeVar(HttpServletRequest request, @SessionAttribute("option") Integer option,
                          @RequestParam(name = "taskIds", required = false) String[] taskIds,
                          @RequestParam(name="numPages") int numPages,
                          Model model) throws Exception {

        if (taskIds != null) {
            List<String> filePaths = ProcessingTasks(taskIds, option, numPages);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                for (String filePath : filePaths) {
                    Path path = Paths.get(filePath);
                    zipOutputStream.putNextEntry(new ZipEntry(path.getFileName().toString()));
                    Files.copy(path, zipOutputStream);
                    zipOutputStream.closeEntry();
                }
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=files.zip");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(byteArrayOutputStream.size())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(byteArrayInputStream));
        }
        return ResponseEntity.status(500).build(); 
    }

    private List<String> ProcessingTasks(String[] tasksIds, int option, int numPages) throws Exception {
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

        return Solutions.mainFunc(chosenTasks, params, option, numPages);
    }
}
