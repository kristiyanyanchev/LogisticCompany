package org.logistic.company.logisticcompany.persistance.service;

import org.logistic.company.logisticcompany.persistance.models.Package;
import org.logistic.company.logisticcompany.persistance.repos.PackageRepostiory;
import org.logistic.company.logisticcompany.persistance.service.dto.PackageDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class PackageService {
    private final PackageRepostiory packageRepostiory;
    private final UserService userService;
    private final OfficeService officeService;

    public PackageService(PackageRepostiory packageRepostiory, UserService userService, OfficeService officeService) {
        this.packageRepostiory = packageRepostiory;
        this.userService = userService;
        this.officeService = officeService;
    }
    public Package getPackageById(Long id) {
        return packageRepostiory.findById(id).orElse(null);
    }

    public void deletePackage(Long id){
        packageRepostiory.deleteById(id);
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
    public List<Package> getPackagesReceivedOrSendBy(String username){
        List<Package> packages = getPackagesReceivedBy(username);
        packages.addAll(getPackagesSendBy(username));

        return packages;
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

    private Package convertFromDto(PackageDTO dto) {
        Package pkg;
        if(dto.getId() == null) {
            pkg = new Package();
        }
        else{
            pkg = getPackageById(dto.getId());
        }
        if(!dto.getDestination().isEmpty()) {
            pkg.setDestination(officeService.getOffice(dto.getDestination()));
        }

        if(!dto.getSource().isEmpty()) {
            pkg.setSource(officeService.getOffice(dto.getSource()));
        }


        pkg.setPrice(dto.getPrice());
        pkg.setStatus(dto.getStatus());
        pkg.setEmployee(userService.findByUsername(dto.getEmployee()));
        pkg.setRecipient(userService.findByUsername(dto.getRecipient()));
        pkg.setSender(userService.findByUsername(dto.getSender()));
        pkg.setSenderAddress(dto.getSenderAddress());
        pkg.setRecipientAddress(dto.getRecipientAddress());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US );

        pkg.setReceivedAt(LocalDate.parse(dto.getReceivedAt(), formatter));
        pkg.setSendAt(LocalDate.parse(dto.getSendAt(), formatter));
        return pkg;

    }

    public PackageDTO getPackageDTOById(long id){
        Package pkg = getPackageById(id);
        PackageDTO dto = new PackageDTO();
        dto.setId(id);
        if(pkg.getDestination() != null) {
            dto.setDestination(pkg.getDestination().getName());
        }
        if(pkg.getSource() != null) {
            dto.setSource(pkg.getSource().getName());
        }

        dto.setPrice(pkg.getPrice());
        dto.setStatus(pkg.getStatus());
        dto.setSender(pkg.getSender().getUsername());
        dto.setEmployee(pkg.getEmployee().getUsername());
        dto.setRecipient(pkg.getRecipient().getUsername());
        dto.setSendAt(pkg.getSendAt().toString());
        dto.setReceivedAt(pkg.getReceivedAt().toString());
        dto.setSenderAddress(pkg.getSenderAddress());
        dto.setRecipientAddress(pkg.getRecipientAddress());
        return dto;

    }

    public void UpdatePackage(PackageDTO dto)
    {
        Package pkg = convertFromDto(dto);
        packageRepostiory.save(pkg);
    }




}
