package springframeworkadvanced.web.user.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.web.user.dto.LoginDto;
import springframeworkadvanced.web.user.dto.SignupDto;
import springframeworkadvanced.web.user.service.UserService;

import java.time.LocalDateTime;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

        userService.create(signupDto, LocalDateTime.now());
        return "redirect:/login";
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

}
