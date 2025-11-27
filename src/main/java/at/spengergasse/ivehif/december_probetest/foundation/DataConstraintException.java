package at.spengergasse.ivehif.december_probetest.foundation;

import java.time.ZonedDateTime;

public class DataConstraintException extends RuntimeException {

    public static DataConstraintException forViolatedDateConstraint(Class<?> clazz, ZonedDateTime before, ZonedDateTime after) {
        return new DataConstraintException("Violated date constraint (expected: %s < %s) for %s".formatted(before, after, clazz));
    }

    public static DataConstraintException forUnmappedEnumValue(char value, Class<?> enumClass) {
        return new DataConstraintException("Unmapped enum value '%c' for enum %s".formatted(value, enumClass));
    }

    public static DataConstraintException forNullValue(Class<?> clazz) {
        return new DataConstraintException("Invalid null value provided for class %s".formatted(clazz));
    }

    private DataConstraintException(String message) {
        super(message);
    }
}
