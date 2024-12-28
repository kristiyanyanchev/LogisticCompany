package org.logistic.company.logisticcompany.persistance.service;

import org.logistic.company.logisticcompany.persistance.models.Office;
import org.logistic.company.logisticcompany.persistance.repos.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {
    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }
    public List<Office> getOffices(){
        return officeRepository.getAllBy();
    }

    public Office getOffice(String name) {
        return officeRepository.findByName(name).get(0);
    }
}
