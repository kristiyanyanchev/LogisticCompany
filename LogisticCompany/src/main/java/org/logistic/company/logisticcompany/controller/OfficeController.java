package org.logistic.company.logisticcompany.controller;

import org.logistic.company.logisticcompany.persistance.service.OfficeService;
import org.logistic.company.logisticcompany.persistance.service.dto.OfficeDTO;
import org.logistic.company.logisticcompany.persistance.service.dto.PackageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OfficeController {
    @Autowired
    OfficeService officeService;

    @GetMapping("/office")
    public String getAllOffices(Model model) {
        model.addAttribute("offices",officeService.getOffices());
        return "office/getAll";
    }

    @GetMapping("/office/update")
    public String updateOfficeForm(@RequestParam("office") int office,Model model) {
        model.addAttribute("dto",officeService.getOfficeDtoById(office));
        return "office/updateOffice";
    }
    @GetMapping("/office/create")
    public String createOfficeForm(Model model) {
        model.addAttribute("dto",new OfficeDTO());
        return "office/updateOffice";
    }

    @GetMapping("/office/delete")
    public RedirectView delete(@RequestParam("office") int office, Model model) {
        officeService.deleteOffice(office);
        return new RedirectView("/office");
    }

    @PostMapping("/office")
    public RedirectView updateOffice(@ModelAttribute OfficeDTO dto, Model model) {
        officeService.updateOffice(dto);
        model.addAttribute("offices",officeService.getOffices());
        return new RedirectView("/office");
    }

}
