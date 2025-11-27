package at.spengergasse.ivehif.december_probetest.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

// We need @SuperBuilder here, because otherwise Lombok ignores
// the builder declared in Medium, in which case we couldn't
// access the attributes in Medium.
@SuperBuilder

@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Videogames")
@PrimaryKeyJoinColumn(
        foreignKey = @ForeignKey(name = "FK_Videogame_Medium")
)
public class Videogame extends Medium {

    private GamePlatform platform;
    private String publisher;
    private AgeRating ageRating;

}
