package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.domain.AgeRating;
import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// See BirthdayConverter for short explanation of autoApply.
@Converter(autoApply = true)
public class AgeRatingConverter implements AttributeConverter<AgeRating, Character> {

    @Override
    public Character convertToDatabaseColumn(AgeRating ageRating) {
        return switch (ageRating) {
            case null -> null;
            // In this case our policy of mapping an enum
            // to a single character meets with a logical barrier.
            // Looking into the database, without knowledge about
            // this mapping makes the meaning of those numeric
            // values unclear.
            case USK0 -> '0';
            case USK6 -> '1';
            case USK12 -> '2';
            case USK16 -> '3';
            case USK18 -> '4';
        };
    }

    @Override
    public AgeRating convertToEntityAttribute(Character character) {
        return switch (character) {
            case null -> null;
            case '0' -> AgeRating.USK0;
            case '1' -> AgeRating.USK6;
            case '2' -> AgeRating.USK12;
            case '3' -> AgeRating.USK16;
            case '4' -> AgeRating.USK18;
            default -> throw DataConstraintException.forUnmappedEnumValue(character, AgeRating.class);
        };
    }
}
