package at.spengergasse.ivehif.december_probetest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

@Builder

@Getter
@Setter

@Entity
@Table(name = "Rentals")
public class Rental {
    @EmbeddedId
    private RentalId id;

    private RentalPeriod rentalPeriod;
    private BigDecimal price;

    // Why not CascadeType.REMOVE? When using REMOVE, the deletion of
    // a Rental causes the associated Customer AND Medium rows to be
    // removed. But we don't want that to happen, because in the worst
    // case this could cause a chain reaction that clears most of our
    // database.
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(name = "FK_Rental_Customer")
    )
    private Customer customer;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(
            name = "medium_id",
            foreignKey = @ForeignKey(name = "FK_Rental_Medium")
    )
    private Medium medium;

    public record RentalId(
            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
            @SequenceGenerator(name = "rentalSeq", sequenceName = "rental_seq", allocationSize = 1)
            @NotNull
            Long id) {}
}
