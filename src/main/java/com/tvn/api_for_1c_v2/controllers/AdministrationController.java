package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.UserService;
import com.tvn.api_for_1c_v2.persistence.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class AdministrationController {
    private UserService userService;

    @GetMapping("/user")
    public String userList(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);

        return "userList";
    }

}
