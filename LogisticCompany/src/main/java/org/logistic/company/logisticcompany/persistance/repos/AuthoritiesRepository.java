package org.logistic.company.logisticcompany.persistance.repos;
import org.logistic.company.logisticcompany.persistance.models.Authority;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AuthoritiesRepository extends CrudRepository<Authority, AuthorityPK> {
    public List<Authority> findByUsername(User username);
}
