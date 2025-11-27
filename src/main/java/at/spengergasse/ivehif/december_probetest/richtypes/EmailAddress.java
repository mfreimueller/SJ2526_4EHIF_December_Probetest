package at.spengergasse.ivehif.december_probetest.richtypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Column(name = "email") @Email String value) {

}
