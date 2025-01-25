package org.logistic.company.logisticcompany.controller;

import org.logistic.company.logisticcompany.persistance.models.Package;
import org.logistic.company.logisticcompany.persistance.service.OfficeService;
import org.logistic.company.logisticcompany.persistance.service.PackageService;
import org.logistic.company.logisticcompany.persistance.service.UserService;
import org.logistic.company.logisticcompany.persistance.service.dto.PackageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
public class PackageController {
    @Autowired
    PackageService packageService;
    @Autowired
    OfficeService officeService;
    @Autowired
    private UserService userService;

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
    @GetMapping("/package/getPackagesSendBySearch")
    public String getPackagesRegisteredBySenderSearch( Model model) {
        return "package/getPackagesSendByClientSearch";
    }

    @GetMapping("package/getPackagesForCurrentUser")
    public String getPackagesForCurrentUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("packages", packageService.getPackagesReceivedBy(currentPrincipalName));
        return "package/getPackagesTable";
    }



    @GetMapping("/package/getPackagesReceivedBySearch")
    public String getPackagesRegisteredReceivedBySearch( Model model) {
        return "package/getPackagesReceivedByClientSearch";
    }

    @GetMapping("/package/getPackagesRegisteredBySearch")
    public String getPackagesRegisteredBySearch( Model model) {
        return "package/getPackagesReceivedByClientSearch";
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
    @GetMapping("/package/getPackagesSendButNotDelivered")
    public String getPackagesSendButNotDelivered(Model model) {
        model.addAttribute("packages", packageService.getSendButNotDeliveredPackages());
        return "package/getPackagesTable";
    }

    @GetMapping("/package/incomeInput")
    public String getPackagesReceivedBy(Model model) {
        return "package/getIncomeForm";
    }


    @GetMapping("/package/income")
    public String getPackagesReceivedBy(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate, Model model) {
        model.addAttribute("revenue", packageService.getAllIncome(startDate, endDate));
        return "package/getIncome";
    }

    @PostMapping("/package")
    public RedirectView packageSubmit(@ModelAttribute PackageDTO pkgDto, Model model) {
        model.addAttribute("dto", pkgDto);
        packageService.UpdatePackage(pkgDto);
        return new RedirectView("/package/getAll");
    }

    @GetMapping("/package/update")
    public String updatePackages( @RequestParam("package") long id, Model model) {
        model.addAttribute("offices",officeService.getOffices());
        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("clients", userService.findAllClients());
        model.addAttribute("dto", packageService.getPackageDTOById(id));

        return "package/updatePackage";
    }
    @GetMapping("/package/create")
    public String createPackages(Model model) {
        Package pkg = new Package();

        model.addAttribute("package", null);
        model.addAttribute("offices",officeService.getOffices());
        model.addAttribute("employees", userService.findAllEmployees());
        model.addAttribute("clients", userService.findAllClients());
        model.addAttribute("dto", new PackageDTO());

        return "package/updatePackage";
    }
    @GetMapping("/package/delete")
    public RedirectView packageDelete( @RequestParam("package") long id, Model model) {
        packageService.deletePackage(id);
        return new RedirectView("/package/getAll");
    }




}

