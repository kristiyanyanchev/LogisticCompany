package org.logistic.company.logisticcompany.persistance.repos;

import org.logistic.company.logisticcompany.persistance.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String lastName);
    List<User> findByEmail(String email);
    List<User> findByOfficeId(Integer officeId);
    List<User> findByRole(String role);

}
