package springframeworkadvanced.web.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springframeworkadvanced.web.user.dto.LoginDto;
import springframeworkadvanced.web.user.dto.SignupDto;
import springframeworkadvanced.web.user.service.UserService;

import java.time.LocalDateTime;

@Controller
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public String createUser(@Valid SignupDto signupDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.signup",
                    bindingResult
            );
            redirectAttributes.addFlashAttribute("signup", signupDto);
            return "redirect:/signup";
        }

        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        userService.create(signupDto, LocalDateTime.now());
        return "redirect:/login";
    }

    @GetMapping("/logoutout")
    public String logout(HttpServletResponse response) {
        // TODO : /logout 경로가 다른 컨트롤러랑 겹치는데 원인을 모르겠음

        Cookie tokenCookie = createTokenCookie(null, 0);
        response.addCookie(tokenCookie);

        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new LoginDto());
        return "user/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        if (!model.containsAttribute("signup"))
            model.addAttribute("signup", new SignupDto());

        return "user/signup";
    }

    private Cookie createTokenCookie(String token, int age) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        return cookie;
    }
}
