package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.Customer;
import at.spengergasse.ivehif.december_probetest.domain.Medium;
import at.spengergasse.ivehif.december_probetest.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Rental.RentalId> {

    List<Rental> findByCustomer(Customer customer);
    List<Rental> findByMedium(Medium medium);

}
