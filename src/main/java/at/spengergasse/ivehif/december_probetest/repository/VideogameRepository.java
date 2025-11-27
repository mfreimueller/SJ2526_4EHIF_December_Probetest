package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.AgeRating;
import at.spengergasse.ivehif.december_probetest.domain.Medium;
import at.spengergasse.ivehif.december_probetest.domain.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Medium.MediumId> {

    List<Videogame> findByAgeRating(AgeRating ageRating);

}
