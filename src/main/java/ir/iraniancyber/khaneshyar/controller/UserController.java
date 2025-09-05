package ir.iraniancyber.khaneshyar.controller;

import ir.iraniancyber.khaneshyar.dto.UserDto;
import ir.iraniancyber.khaneshyar.model.Role;
import ir.iraniancyber.khaneshyar.model.User;
import ir.iraniancyber.khaneshyar.service.user.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationManager authenticationManager;

    public UserController(PasswordEncoder passwordEncoder, CustomUserDetailService customUserDetailService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailService = customUserDetailService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")//todo call from front
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setNationality(userDto.getNationality());
        user.setNationalCode(userDto.getNationalCode());
        user.setRole(Role.USER);
        customUserDetailService.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}
