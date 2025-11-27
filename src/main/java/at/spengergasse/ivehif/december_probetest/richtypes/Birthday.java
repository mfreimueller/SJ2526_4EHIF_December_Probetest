package at.spengergasse.ivehif.december_probetest.richtypes;

import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

@Embeddable
public record Birthday(@Column(name = "birthday") @NotNull ZonedDateTime value) {

    public Birthday {
        if (value == null) {
            throw DataConstraintException.forNullValue(Birthday.class);
        }

        // As per UML: { age >= 14 }
        // We create a date placed exactly 14 years ago and compare the given
        // birthday with this value. If the birthday lies after the calculated
        // date, the user is too young and thus violates our constraint.
        final ZonedDateTime minimumYear = ZonedDateTime.now().minusYears(14);

        if (value.isAfter(minimumYear)) {
            throw DataConstraintException.forViolatedDateConstraint(Birthday.class, value, minimumYear);
        }
    }

}
