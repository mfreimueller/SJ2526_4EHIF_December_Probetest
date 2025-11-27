package at.spengergasse.ivehif.december_probetest.domain;

import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

@Embeddable
public record RentalPeriod(@Column(name = "start_ts") @NotNull ZonedDateTime start, @Column(name = "end_ts") ZonedDateTime end) {

    public RentalPeriod {
        // We need to make sure that the start date lies before the end date,
        // as described in the UML ( { start < end } ).
        if (end != null && start.isAfter(end)) {
            throw DataConstraintException.forViolatedDateConstraint(RentalPeriod.class, start, end);
        }
    }

}
