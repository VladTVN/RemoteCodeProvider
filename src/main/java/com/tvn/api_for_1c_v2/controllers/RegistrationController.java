package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.UserService;
import com.tvn.api_for_1c_v2.persistence.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class RegistrationController {
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String password2, User user, Model model){

        boolean isConfirmEmpty = !StringUtils.hasLength(password2);
        if (isConfirmEmpty){
            model.addAttribute("password2Error","Password confirmation cant be empty");
            return "registration";
        }
        if (user.getPassword() !=null && !user.getPassword().equals(password2)){
            model.addAttribute("password2Error", "Passwords are different");
            return "registration";
        }

        if (!userService.addUser(user)){
            model.addAttribute("usernameError", "User already exist");
            return "registration";
        }

        return "redirect:home";
    }

}
