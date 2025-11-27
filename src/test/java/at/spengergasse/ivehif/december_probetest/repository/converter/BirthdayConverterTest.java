package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import at.spengergasse.ivehif.december_probetest.richtypes.Birthday;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BirthdayConverterTest {

    private final BirthdayConverter converter = new BirthdayConverter();

    @Test
    void can_convert_to_db_value_and_back() {
        final ZonedDateTime date = ZonedDateTime.of(1999, 3, 7, 0, 0, 0, 0, ZoneId.systemDefault());
        final Birthday birthday = new Birthday(date);

        var result = converter.convertToDatabaseColumn(birthday);
        assertThat(result).isEqualTo(date);
        assertThat(converter.convertToEntityAttribute(result)).isEqualTo(birthday);
    }

    @Test
    void throws_when_too_young() {
        assertThrows(DataConstraintException.class, () -> converter.convertToEntityAttribute(ZonedDateTime.now()));
    }

    @Test
    void throws_exception_on_null_value() {
        assertThrows(DataConstraintException.class, () -> converter.convertToEntityAttribute(null));
        assertThrows(DataConstraintException.class, () -> converter.convertToDatabaseColumn(null));
    }

}