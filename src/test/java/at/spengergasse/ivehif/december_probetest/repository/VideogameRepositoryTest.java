package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.AgeRating;
import at.spengergasse.ivehif.december_probetest.domain.GamePlatform;
import at.spengergasse.ivehif.december_probetest.domain.Videogame;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VideogameRepositoryTest {

    @Autowired
    private VideogameRepository videogameRepository;

    private List<Videogame> testVideogames = List.of();

    @BeforeEach
    void setUp() {
        var videogames = List.of(
                Videogame.builder()
                        .title("Elden Ring")
                        .genre("RPG")
                        .releaseYear(1968)
                        .publisher("Bandai Namco Entertainment")
                        .platform(GamePlatform.PC)
                        .ageRating(AgeRating.USK16)
                        .build(),
                Videogame.builder()
                        .title("God of War Ragnarök")
                        .genre("Action-Adventure")
                        .releaseYear(2022)
                        .publisher("Sony Interactive Entertainment")
                        .platform(GamePlatform.PLAYSTATION)
                        .ageRating(AgeRating.USK18)
                        .build()
        );

        testVideogames = videogameRepository.saveAll(videogames);
    }

    @AfterEach
    void tearDown() {
        videogameRepository.deleteAll(testVideogames);
    }

    @Test
    void can_load_all() {
        var videogames = videogameRepository.findAll();
        assertThat(videogames).isNotNull();
        assertThat(videogames).returns(2, List::size);
    }

    @Test
    void can_find_by_age_rating() {
        var videogames = videogameRepository.findByAgeRating(AgeRating.USK16);
        assertThat(videogames).isNotNull();
        assertThat(videogames).returns(1, List::size);
    }

    @Test
    void can_save_and_reread() {
        var videogame = Videogame.builder()
                .title("God of War Ragnarök")
                .genre("Action-Adventure")
                .releaseYear(2022)
                .publisher("Sony Interactive Entertainment")
                .ageRating(AgeRating.USK18)
                .build();

        var saved = videogameRepository.save(videogame);

        assertThat(saved).extracting(Videogame::getId).isNotNull();

        var reread = videogameRepository.findById(saved.getId());
        assertThat(reread).isNotNull();
    }

}