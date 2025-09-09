package ir.iraniancyber.khaneshyar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String showExam() {
        return "showExam";
    }

    @GetMapping("/saveExam")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String saveExam() {
        return "saveExam";
    }

    @GetMapping("/saveQuestion")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String saveQuestion() {
        return "saveQuestion";
    }

    @GetMapping("/showQuestion")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String showQuestion() {
        return "showQuestion";
    }

    @GetMapping("/updateQuestion")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String updateQuestion() {
        return "updateQuestion";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/showExamQuestion")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String ShowExamQuestion() {
        return "showExamQuestion";
    }

    @GetMapping("/theNewestTests")
    public String theNewestTests() {
        return "theNewestTests";
    }

    @GetMapping("/403")
    public String accessDen() {
        return "403";
    }

    @GetMapping("/allExam")
    public String allExam() {
        return "theNewestTests";
    }

    @GetMapping("/startExam")
    public String startExam() {
        return "startExam";
    }
}
