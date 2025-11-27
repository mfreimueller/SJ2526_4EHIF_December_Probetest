package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// MediumRepositoryTest is special, as Medium is abstract. Therefor we want to test that
// one can retrieve both video games and movies with this repository.
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MediumRepositoryTest {

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private VideogameRepository videogameRepository;

    private Movie testMovie;
    private Videogame testVideogame;

    @BeforeEach
    void setUp() {
        testMovie = Movie.builder()
                .title("2001: A Space Odyssey")
                .genre("Science Fiction")
                .releaseYear(1968)
                .durationInMinutes(149)
                .director("Stanley Kubrick")
                .ageRating(AgeRating.USK12)
                .build();
        movieRepository.save(testMovie);

        testVideogame = Videogame.builder()
                .platform(GamePlatform.PC)
                .genre("RPG")
                .title("Elden Ring")
                .publisher("Bandai Namco Entertainment")
                .releaseYear(2022)
                .ageRating(AgeRating.USK16)
                .build();
        videogameRepository.save(testVideogame);
    }

    @AfterEach
    void tearDown() {
        movieRepository.delete(testMovie);
        videogameRepository.delete(testVideogame);
    }

    @Test
    void can_read_all_medias() {
        var media = mediumRepository.findAll();

        assertThat(media).returns(false, List::isEmpty);
        assertThat(media).returns(2, List::size);
    }

}