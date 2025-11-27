package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.domain.GamePlatform;
import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import at.spengergasse.ivehif.december_probetest.richtypes.Birthday;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.ZonedDateTime;

// See BirthdayConverter for short explanation of autoApply.
@Converter(autoApply = true)
public class GamePlatformConverter implements AttributeConverter<GamePlatform, Character> {

    @Override
    public Character convertToDatabaseColumn(GamePlatform gamePlatform) {
        return switch (gamePlatform) {
            case null -> null;
            case PC -> 'P';
            case XBOX -> 'X';
            case SWITCH -> 'S';
            case PLAYSTATION -> 'Y';
        };
    }

    @Override
    public GamePlatform convertToEntityAttribute(Character character) {
        return switch (character) {
            case null -> null;
            case 'P' -> GamePlatform.PC;
            case 'X' -> GamePlatform.XBOX;
            case 'S' -> GamePlatform.SWITCH;
            case 'Y' -> GamePlatform.PLAYSTATION;
            default -> throw DataConstraintException.forUnmappedEnumValue(character, GamePlatform.class);
        };
    }
}
