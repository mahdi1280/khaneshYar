package ir.iraniancyber.khaneshyar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/showExam")
    public String showExam() {
        return "showExam";
    }

    @GetMapping("/saveExam")
    public String saveExam() {
        return "saveExam";
    }

    @GetMapping("/saveQuestion")
    public String saveQuestion() {
        return "saveQuestion";
    }

    @GetMapping("/showQuestion")
    public String showQuestion() {
        return "showQuestion";
    }
}
