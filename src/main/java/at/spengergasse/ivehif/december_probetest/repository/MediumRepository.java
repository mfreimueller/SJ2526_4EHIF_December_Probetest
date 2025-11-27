package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<Medium, Medium.MediumId> {
}
