package pro.sky.todolist.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.todolist.service.GenerateService;

@RequestMapping("/generate")
@RestController
public class GenerateController {

    private final GenerateService generateService;

    public GenerateController(GenerateService generateService) {
        this.generateService = generateService;
    }

    @PostMapping("/users")
    public void generateUsers(@RequestParam int count) {
        generateService.generateUsers(count);
    }


    @PostMapping("/labels")
    public void generateLabels(@RequestParam int count) {
        generateService.generateLabels(count);
    }

    @PostMapping("/tasks")
    public void generateTasks(@RequestParam int count) {
        generateService.generateTasks(count);
    }

}
