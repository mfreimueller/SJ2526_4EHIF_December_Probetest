package at.spengergasse.ivehif.december_probetest.repository;

import at.spengergasse.ivehif.december_probetest.domain.Address;
import at.spengergasse.ivehif.december_probetest.domain.Customer;
import at.spengergasse.ivehif.december_probetest.richtypes.Birthday;
import at.spengergasse.ivehif.december_probetest.richtypes.EmailAddress;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        final ZonedDateTime birthday = ZonedDateTime.of(1990, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());

        testCustomer = Customer.builder()
                .birthday(new Birthday(birthday))
                .address(new Address("Spengergasse 20", "1050", "Wien"))
                .email(new EmailAddress("max@muster.man"))
                .firstname("Max")
                .lastname("Muster Man")
                .build();

        customerRepository.save(testCustomer);
    }

    @AfterEach
    void tearDown() {
        customerRepository.delete(testCustomer);
    }

    @Test
    void can_save_and_reread() {
        final ZonedDateTime birthday = ZonedDateTime.of(1990, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());

        var customer = Customer.builder()
                .birthday(new Birthday(birthday))
                .address(new Address("Spengergasse 20", "1050", "Wien"))
                .email(new EmailAddress("max@muster.man"))
                .firstname("Max")
                .lastname("Muster Man")
                .build();

        var saved = customerRepository.save(customer);

        assertThat(saved).extracting(Customer::getId).isNotNull();

        var reread = customerRepository.findById(saved.getId());
        assertThat(reread).isNotNull();
    }

    @Test
    void can_find_by_firstname_and_lastname() {
        var retrieved = customerRepository.findByFirstnameAndLastname(testCustomer.getFirstname(), testCustomer.getLastname());
        assertThat(retrieved).isNotNull();
    }

}