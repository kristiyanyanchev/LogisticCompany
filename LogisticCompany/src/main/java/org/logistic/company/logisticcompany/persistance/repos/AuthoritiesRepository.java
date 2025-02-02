package org.logistic.company.logisticcompany.persistance.repos;

import org.logistic.company.logisticcompany.persistance.models.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends CrudRepository<Authority, AuthorityPK> {
}
