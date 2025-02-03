package org.logistic.company.logisticcompany.persistance.repos;

import org.logistic.company.logisticcompany.persistance.models.Package;
import org.logistic.company.logisticcompany.persistance.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageRepostiory extends CrudRepository<Package, Long> {
    List<Package> findByEmployee(User user);
    List<Package> findByStatus(String status);
    List<Package> findByRecipient(User user);
    List<Package> findBySender(User user);


    List<Package> getAllBy();

}
