package org.logistic.company.logisticcompany.config;

import org.logistic.company.logisticcompany.persistance.models.User;
import org.logistic.company.logisticcompany.persistance.service.UserService;
import org.logistic.company.logisticcompany.persistance.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addUserToModel(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userService.findByUsername(userDetails.getUsername());
            UserDTO userDTO = new UserDTO();

            userDTO.setUsername(user.getUsername());
            userDTO.setRole(user.getRole());
            userDTO.setOffice(user.getRole().equals("client") ? "" : user.getOffice().getName());

            model.addAttribute("user", userDTO);
            model.addAttribute("user", user);
        }
    }
}