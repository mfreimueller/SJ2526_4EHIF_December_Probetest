package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.domain.AgeRating;
import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AgeRatingConverterTest {

    private final AgeRatingConverter converter = new AgeRatingConverter();

    @ParameterizedTest
    @MethodSource("mappingData")
    void can_convert_to_db_value_and_back(AgeRating ageRating, String dbValue) {
        var result = converter.convertToDatabaseColumn(ageRating);
        assertThat(result).isEqualTo(dbValue);
        assertThat(converter.convertToEntityAttribute(result)).isEqualTo(ageRating);
    }

    @Test
    void throws_exception_on_unmapped_value() {
        assertThrows(DataConstraintException.class, () -> converter.convertToEntityAttribute("X"));
    }

    @Test
    void can_convert_null_values_safely() {
        assertThat(converter.convertToEntityAttribute(null)).isNull();
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    private static Stream<Arguments> mappingData() {
        return Stream.of(
                Arguments.of(AgeRating.USK0, "0"),
                Arguments.of(AgeRating.USK6, "1"),
                Arguments.of(AgeRating.USK12, "2"),
                Arguments.of(AgeRating.USK16, "3"),
                Arguments.of(AgeRating.USK18, "4")
        );
    }

}