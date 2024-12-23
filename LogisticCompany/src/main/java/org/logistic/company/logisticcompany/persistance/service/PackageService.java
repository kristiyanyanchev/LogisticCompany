package org.logistic.company.logisticcompany.persistance.service;

import org.logistic.company.logisticcompany.persistance.models.Package;
import org.logistic.company.logisticcompany.persistance.models.User;
import org.logistic.company.logisticcompany.persistance.repos.PackageRepostiory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {
    private final PackageRepostiory packageRepostiory;
    private final UserService userService;

    public PackageService(PackageRepostiory packageRepostiory, UserService userService) {
        this.packageRepostiory = packageRepostiory;
        this.userService = userService;
    }

    public List<Package> getPackages(){
        return packageRepostiory.getAllBy();
    }

    public List<Package> getPackagesRegisteredBy(String username){
       return packageRepostiory.findByEmployee(userService.findByUsername(username));
    }

    public List<Package> getSendButNotDeliveredPackages(){
        return packageRepostiory.findByStatus("In Transit");
    }

    public List<Package> getPackagesSendBy(String username){
        return packageRepostiory.findBySender(userService.findByUsername(username));
    }

    public List<Package> getPackagesReceivedBy(String username){
        return packageRepostiory.findByRecipient(userService.findByUsername(username));
    }

    public BigDecimal getAllIncome(LocalDate startDate, LocalDate endDate){
        List<Package> packages = getPackages();
        BigDecimal totalIncome = new BigDecimal(0);


        for (Package p : packages) {
            if(p.getReceivedAt().isBefore(endDate) && p.getReceivedAt().isAfter(startDate)) {
                totalIncome =  totalIncome.add(p.getPrice());
            }
        }
        return  totalIncome;
    }




}
