package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private List<Movie> testMovies = List.of();

    @BeforeEach
    void setUp() {
        var movies = List.of(
                Movie.builder()
                        .title("2001: A Space Odyssey")
                        .genre("Science Fiction")
                        .releaseYear(1968)
                        .durationInMinutes(149)
                        .director("Stanley Kubrick")
                        .ageRating(AgeRating.USK12)
                        .build(),
                Movie.builder()
                        .title("Alien")
                        .genre("Science Fiction")
                        .releaseYear(1979)
                        .durationInMinutes(116)
                        .director("Ridley Scott")
                        .ageRating(AgeRating.USK18)
                        .build()
        );

        testMovies = movieRepository.saveAll(movies);
    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll(testMovies);
    }

    @Test
    void can_load_all() {
        var movies = movieRepository.findAll();
        assertThat(movies).isNotNull();
        assertThat(movies).returns(2, List::size);
    }

    @Test
    void can_find_by_age_rating() {
        var movies = movieRepository.findByAgeRating(AgeRating.USK12);
        assertThat(movies).isNotNull();
        assertThat(movies).returns(1, List::size);
    }

    @Test
    void can_find_by_director() {
        var movies = movieRepository.findByDirector("Stanley Kubrick");
        assertThat(movies).isNotNull();
        assertThat(movies).returns(1, List::size);
    }

    @Test
    void can_save_and_reread() {
        var movie = Movie.builder()
                .title("Alien")
                .genre("Science Fiction")
                .releaseYear(1979)
                .durationInMinutes(116)
                .director("Ridley Scott")
                .ageRating(AgeRating.USK16)
                .build();

        var saved = movieRepository.save(movie);

        assertThat(saved).extracting(Movie::getId).isNotNull();

        var reread = movieRepository.findById(saved.getId());
        assertThat(reread).isNotNull();
    }

}