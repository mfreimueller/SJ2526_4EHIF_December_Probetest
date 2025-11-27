package at.spengergasse.ivehif.december_probetest.domain;

import at.spengergasse.ivehif.december_probetest.repository.converter.AgeRatingConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Check;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

@SuperBuilder // See explanation in Videogame.

@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Movies")
@PrimaryKeyJoinColumn(
        foreignKey = @ForeignKey(name = "FK_Movie_Medium")
)
public class Movie extends Medium {

    private String director;
    private Integer durationInMinutes;

    @Convert(converter = AgeRatingConverter.class)
    private AgeRating ageRating;

}
