package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.domain.GamePlatform;
import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GamePlatformConverterTest {

    private final GamePlatformConverter converter = new GamePlatformConverter();

    @ParameterizedTest
    @MethodSource("mappingData")
    void can_convert_to_db_value_and_back(GamePlatform gamePlatform, Character dbValue) {
        var result = converter.convertToDatabaseColumn(gamePlatform);
        assertThat(result).isEqualTo(dbValue);
        assertThat(converter.convertToEntityAttribute(result)).isEqualTo(gamePlatform);
    }

    @Test
    void throws_exception_on_unmapped_value() {
        assertThrows(DataConstraintException.class, () -> converter.convertToEntityAttribute('0'));
    }

    @Test
    void can_convert_null_values_safely() {
        assertThat(converter.convertToEntityAttribute(null)).isNull();
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    private static Stream<Arguments> mappingData() {
        return Stream.of(
                Arguments.of(GamePlatform.PC, 'P'),
                Arguments.of(GamePlatform.XBOX, 'X'),
                Arguments.of(GamePlatform.SWITCH, 'S'),
                Arguments.of(GamePlatform.PLAYSTATION, 'Y')
        );
    }

}