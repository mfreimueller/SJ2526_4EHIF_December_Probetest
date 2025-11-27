package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import at.spengergasse.ivehif.december_probetest.richtypes.Birthday;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.ZonedDateTime;

// By using autoApply = true, Spring automatically finds occurrences
// of Birthday and applies this converter.
@Converter(autoApply = true)
public class BirthdayConverter implements AttributeConverter<Birthday, ZonedDateTime> {

    @Override
    public ZonedDateTime convertToDatabaseColumn(Birthday birthday) {
        if (birthday == null) {
            throw DataConstraintException.forNullValue(BirthdayConverter.class);
        }

        return birthday.value();
    }

    @Override
    public Birthday convertToEntityAttribute(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            throw DataConstraintException.forNullValue(BirthdayConverter.class);
        }

        return new Birthday(zonedDateTime);
    }
}
