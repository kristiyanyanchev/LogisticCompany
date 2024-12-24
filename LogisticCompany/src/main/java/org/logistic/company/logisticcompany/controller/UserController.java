package org.logistic.company.logisticcompany.controller;

import org.logistic.company.logisticcompany.persistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/employees")
    public String getAll(Model model) {
        model.addAttribute("users",userService.findAllEmployees());
        return "user/employees";
    }
}
