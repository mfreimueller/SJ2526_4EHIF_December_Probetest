package at.spengergasse.ivehif.december_probetest.repository.converter;

import at.spengergasse.ivehif.december_probetest.foundation.DataConstraintException;
import at.spengergasse.ivehif.december_probetest.richtypes.EmailAddress;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// See BirthdayConverter for short explanation of autoApply.
@Converter(autoApply = true)
public class EmailAddressConverter implements AttributeConverter<EmailAddress, String> {

    @Override
    public String convertToDatabaseColumn(EmailAddress emailAddress) {
        if (emailAddress == null) {
            throw DataConstraintException.forNullValue(EmailAddressConverter.class);
        }

        return emailAddress.value();
    }

    @Override
    public EmailAddress convertToEntityAttribute(String emailAddress) {
        if (emailAddress == null) {
            throw DataConstraintException.forNullValue(EmailAddressConverter.class);
        }

        return new EmailAddress(emailAddress);
    }
}
