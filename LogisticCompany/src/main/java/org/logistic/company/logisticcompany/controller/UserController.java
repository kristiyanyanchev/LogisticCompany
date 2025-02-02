package org.logistic.company.logisticcompany.controller;

import org.logistic.company.logisticcompany.persistance.service.OfficeService;
import org.logistic.company.logisticcompany.persistance.service.UserService;
import org.logistic.company.logisticcompany.persistance.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OfficeService officeService;

    @GetMapping("/users/employees")
    public String getAll(Model model) {
        model.addAttribute("users",userService.findAllEmployees());
        return "user/employees";
    }

    @GetMapping("/users/clients")
    public String getAllClients(Model model) {
        model.addAttribute("users",userService.findAllClients());
        return "user/clients";
    }

    @GetMapping("/users/update")
    public String update(@RequestParam("user") long id,Model model) {
        model.addAttribute("dto", userService.getUserDTOById(id));
        model.addAttribute("offices",officeService.getOffices());
        return "user/updateUser";
    }
    @GetMapping("/users/create")
    public String create(Model model) {
        model.addAttribute("dto", new UserDTO());
        model.addAttribute("offices",officeService.getOffices());
        return "user/updateUser";
    }

    @PostMapping("/users")
    public RedirectView packageSubmit(@ModelAttribute UserDTO usrDto, Model model) {
        model.addAttribute("dto", usrDto);
        userService.updateUser(usrDto);
        return new RedirectView("/users/employees");
    }
    @PostMapping("/register")
    public RedirectView registerSubmit(@ModelAttribute UserDTO usrDto, Model model) {
        model.addAttribute("dto", usrDto);
        userService.updateUser(usrDto);
        return new RedirectView("/login");
    }

    @PostMapping("/users/delete")
    public RedirectView packageDelete(@RequestParam("user") long id, Model model) {
        userService.deleteUser(id);
        return new RedirectView("/users/clients");
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("dto", new UserDTO());
        return "user/register";
    }


}
