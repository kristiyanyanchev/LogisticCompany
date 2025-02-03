package org.logistic.company.logisticcompany.persistance.service;

import org.logistic.company.logisticcompany.persistance.models.Office;
import org.logistic.company.logisticcompany.persistance.models.User;
import org.logistic.company.logisticcompany.persistance.repos.OfficeRepository;
import org.logistic.company.logisticcompany.persistance.service.dto.OfficeDTO;
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

    public void updateOffice(OfficeDTO officeDto) {
        Office office;
        if (officeDto.getId() != 0) {
            office = officeRepository.getFirstById(officeDto.getId());
        }
        else{
            office = new Office();
        }

        office.setName(officeDto.getName());
        office.setCity(officeDto.getCity());
        office.setWorkingHours(officeDto.getWorkingHours());
        office.setAddress(officeDto.getAddress());
        officeRepository.save(office);
    }

    public OfficeDTO getOfficeDtoById(int id) {
        return getOfficeDTO(officeRepository.getFirstById(id));
    }

    public OfficeDTO getOfficeDTO(Office office) {
        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setId(office.getId());
        officeDTO.setName(office.getName());
        officeDTO.setCity(office.getCity());
        officeDTO.setAddress(office.getAddress());
        officeDTO.setWorkingHours(office.getWorkingHours());
        return officeDTO;
    }

    public void deleteOffice(int id) {
        Office office = officeRepository.getFirstById(id);
        officeRepository.delete(office);
    }

}
