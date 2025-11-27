package at.spengergasse.ivehif.december_probetest.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Address(@NotNull String street, @NotNull String postalCode, @NotNull String city) {
}
