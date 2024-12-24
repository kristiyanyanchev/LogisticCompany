package org.logistic.company.logisticcompany.controller;

import org.logistic.company.logisticcompany.persistance.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class PackageController {
    @Autowired
    PackageService packageService;

    @GetMapping
    public String index() {
        return "package/index";
    }

    @GetMapping("/package/getAll")
    public String getAll(Model model) {
        model.addAttribute("packages",packageService.getPackages());
        return "package/getAll";
    }

    @GetMapping("/package/getPackagesRegisteredByEmployee")
    public String getPackagesRegisteredByEmployee( @RequestParam("username") String username, Model model) {
        model.addAttribute("packages", packageService.getPackagesRegisteredBy(username));
        return "package/getPackagesTable";
    }

    @GetMapping("/package/getPackagesSendBy")
    public String getPackagesRegisteredBySender( @RequestParam("username") String username, Model model) {
        model.addAttribute("packages", packageService.getPackagesSendBy(username));
        return "package/getPackagesTable";
    }

    @GetMapping("/package/getPackagesReceivedBy")
    public String getPackagesReceivedBy( @RequestParam("username") String username, Model model) {
        model.addAttribute("packages", packageService.getPackagesReceivedBy(username));
        return "package/getPackagesTable";
    }

    @GetMapping("/package/getPackagesReceivedOrSendBy")
    public String getPackagesReceivedOrSendBy( @RequestParam("username") String username, Model model) {
        model.addAttribute("packages", packageService.getPackagesReceivedOrSendBy(username));
        return "package/getPackagesTable";
    }

    @GetMapping("/package/income")
    public String getPackagesReceivedBy(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate, Model model) {
        model.addAttribute("revenue", packageService.getAllIncome(startDate, endDate));
        return "package/getIncome";
    }


}

