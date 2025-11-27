package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.*;
import at.spengergasse.ivehif.december_probetest.richtypes.Birthday;
import at.spengergasse.ivehif.december_probetest.richtypes.EmailAddress;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RentalRepositoryTest {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediumRepository mediumRepository;

    private Customer testCustomer;
    private Medium testMedium;
    private List<Rental> testRentals = List.of();

    @BeforeEach
    void setUp() {
        testCustomer = Customer.builder()
                .email(new EmailAddress("max@muster.man"))
                .firstname("Max")
                .lastname("Muster Man")
                .address(new Address("Spengergasse 20", "1050", "Wien"))
                .birthday(new Birthday(ZonedDateTime.of(1980, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())))
                .build();

        var customer2 = Customer.builder()
                .email(new EmailAddress("bob@anna.com"))
                .firstname("Anna")
                .lastname("Bob")
                .address(new Address("Conrad von Hötzendorf-Platz 1", "2500", "Baden"))
                .birthday(new Birthday(ZonedDateTime.of(1991, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())))
                .build();

        var movie = Movie.builder()
                        .title("2001: A Space Odyssey")
                        .genre("Science Fiction")
                        .releaseYear(1968)
                        .durationInMinutes(149)
                        .director("Stanley Kubrick")
                        .ageRating(AgeRating.USK12)
                        .build();

        testMedium = Videogame.builder()
                .title("God of War Ragnarök")
                .genre("Action-Adventure")
                .releaseYear(2022)
                .publisher("Sony Interactive Entertainment")
                .platform(GamePlatform.PLAYSTATION)
                .ageRating(AgeRating.USK18)
                .build();

        var rentals = List.of(
                Rental.builder()
                        .medium(testMedium)
                        .customer(testCustomer)
                        .price(BigDecimal.valueOf(19.99))
                        .rentalPeriod(new RentalPeriod(ZonedDateTime.now(), null))
                        .build(),
                Rental.builder()
                        .medium(movie)
                        .customer(customer2)
                        .price(BigDecimal.valueOf(8.98))
                        .rentalPeriod(new RentalPeriod(ZonedDateTime.now(), null))
                        .build()
        );

        testRentals = rentalRepository.saveAll(rentals);
    }

    @AfterEach
    void tearDown() {
        rentalRepository.deleteAll(testRentals);
        customerRepository.delete(testCustomer);
        mediumRepository.delete(testMedium);
    }

    @Test
    void can_load_all() {
        var rentals = rentalRepository.findAll();
        assertThat(rentals).isNotNull();
        assertThat(rentals).returns(2, List::size);
    }

    @Test
    void can_find_by_customer() {
        var rentals = rentalRepository.findByCustomer(testCustomer);
        assertThat(rentals).isNotNull();
        assertThat(rentals).returns(1, List::size);
    }

    @Test
    void can_find_by_medium() {
        var rentals = rentalRepository.findByMedium(testMedium);
        assertThat(rentals).isNotNull();
        assertThat(rentals).returns(1, List::size);
    }

    @Test
    void can_save_and_reread() {
        var customer = Customer.builder()
                .email(new EmailAddress("bob@anna.com"))
                .firstname("Anna")
                .lastname("Bob")
                .address(new Address("Conrad von Hötzendorf-Platz 1", "2500", "Baden"))
                .birthday(new Birthday(ZonedDateTime.of(1991, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())))
                .build();

        var movie = Movie.builder()
                .title("2001: A Space Odyssey")
                .genre("Science Fiction")
                .releaseYear(1968)
                .durationInMinutes(149)
                .director("Stanley Kubrick")
                .ageRating(AgeRating.USK12)
                .build();

        var rental = Rental.builder()
                        .medium(movie)
                        .customer(customer)
                        .price(BigDecimal.valueOf(19.99))
                        .rentalPeriod(new RentalPeriod(ZonedDateTime.now(), null))
                        .build();

        var saved = rentalRepository.save(rental);

        assertThat(saved).extracting(Rental::getId).isNotNull();

        var reread = rentalRepository.findById(saved.getId());
        assertThat(reread).isNotNull();
    }

}