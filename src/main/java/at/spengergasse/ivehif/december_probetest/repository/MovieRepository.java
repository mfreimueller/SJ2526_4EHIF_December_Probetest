package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.AgeRating;
import at.spengergasse.ivehif.december_probetest.domain.Medium;
import at.spengergasse.ivehif.december_probetest.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Medium.MediumId> {

    List<Movie> findByAgeRating(AgeRating ageRating);
    List<Movie> findByDirector(String director);

}
