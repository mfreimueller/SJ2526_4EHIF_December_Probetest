package at.spengergasse.ivehif.december_probetest.domain;

import at.spengergasse.ivehif.december_probetest.richtypes.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

@Builder

@Getter
@Setter

@Entity
@Table(name = "Customers")
public class Customer {
    @EmbeddedId
    private CustomerId id;

    @Version
    private Long version;

    private String firstname;
    private String lastname;

    private Address address;

    private Birthday birthday;

    private EmailAddress email;

    @OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Rental> rentedItems;

    public record CustomerId(
            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
            @SequenceGenerator(name = "customerSeq", sequenceName = "customer_seq", allocationSize = 1)
            @NotNull
            Long id) {}
}
