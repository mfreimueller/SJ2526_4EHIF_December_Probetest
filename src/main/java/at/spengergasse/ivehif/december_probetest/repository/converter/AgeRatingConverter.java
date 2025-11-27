package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.domain.AgeRating;
import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// See BirthdayConverter for short explanation of autoApply.
@Converter(autoApply = true)
public class AgeRatingConverter implements AttributeConverter<AgeRating, String> {

    @Override
    public String convertToDatabaseColumn(AgeRating ageRating) {
        return switch (ageRating) {
            case null -> null;
            // In this case our policy of mapping an enum
            // to a single character meets with a logical barrier.
            // Looking into the database, without knowledge about
            // this mapping makes the meaning of those numeric
            // values unclear.
            case USK0 -> "u0";
            case USK6 -> "u6";
            case USK12 -> "u12";
            case USK16 -> "u16";
            case USK18 -> "u18";
        };
    }

    @Override
    public AgeRating convertToEntityAttribute(String character) {
        return switch (character) {
            case null -> null;
            case "u0" -> AgeRating.USK0;
            case "u6" -> AgeRating.USK6;
            case "u12" -> AgeRating.USK12;
            case "u16" -> AgeRating.USK16;
            case "u18" -> AgeRating.USK18;
            default -> throw DataConstraintException.forUnmappedEnumValue(character.charAt(0), AgeRating.class);
        };
    }
}
