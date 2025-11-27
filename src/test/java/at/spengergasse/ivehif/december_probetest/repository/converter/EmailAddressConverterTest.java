package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import at.spengergasse.ivehif.december_probetest.richtypes.Birthday;
import at.spengergasse.ivehif.december_probetest.richtypes.EmailAddress;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmailAddressConverterTest {

    private final EmailAddressConverter converter = new EmailAddressConverter();

    @Test
    void can_convert_to_db_value_and_back() {
        final String email = "max@muster.man";
        final EmailAddress emailAddress = new EmailAddress(email);

        var result = converter.convertToDatabaseColumn(emailAddress);
        assertThat(result).isEqualTo(email);
        assertThat(converter.convertToEntityAttribute(result)).isEqualTo(emailAddress);
    }

    @Test
    void throws_exception_on_null_value() {
        assertThrows(DataConstraintException.class, () -> converter.convertToEntityAttribute(null));
        assertThrows(DataConstraintException.class, () -> converter.convertToDatabaseColumn(null));
    }

}