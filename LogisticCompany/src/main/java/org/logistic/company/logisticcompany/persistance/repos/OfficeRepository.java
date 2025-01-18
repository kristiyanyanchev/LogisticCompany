package org.logistic.company.logisticcompany.persistance.repos;

import org.logistic.company.logisticcompany.persistance.models.Office;
import org.logistic.company.logisticcompany.persistance.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepository extends CrudRepository<Office, Long> {
    List<Office> getAllBy();
    List<Office> findByName(String name);
    List<Office> findByCity(String city);

}
