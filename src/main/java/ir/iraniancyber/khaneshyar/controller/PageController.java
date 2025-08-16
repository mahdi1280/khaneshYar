package ir.iraniancyber.khaneshyar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
    @GetMapping("/showExam")
    public String showExam()
    {
        return "showExam";
    }
    @GetMapping("/saveExam")
    public String saveExam()
    {
        return "saveExam";
    }
}
