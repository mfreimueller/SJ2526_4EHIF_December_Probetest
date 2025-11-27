package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Customer.CustomerId> {

    Customer findByFirstnameAndLastname(String firstname, String lastname);

}
