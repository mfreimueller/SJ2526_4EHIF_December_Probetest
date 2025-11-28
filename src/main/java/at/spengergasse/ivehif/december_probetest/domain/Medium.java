package at.spengergasse.ivehif.december_probetest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

// Needed because Medium is a super class, and we want the builders
// of Videogame/Movie to know about Medium as well:
@SuperBuilder

@Getter
@Setter

@Entity

// Why Joined? Because we reduce data redundancy this way...
// TABLE_PER_CLASS would duplicate all columns from Medium in Videogame
// and Movie.
// SINGLE_TABLE violates the idea of normalization. There are situations
// where this is appropriate, for example when declaring an abstract super
// class that has at most one attribute.
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "Media")
public abstract class Medium {
    @EmbeddedId
    private MediumId id;

    @Version
    private Long version;

    @NotNull
    private String title;

    @Min(1888)
    private Integer releaseYear;

    // tbh genre as String doesn't make much sense...
    // Should either be an enum or another table.
    private String genre;

    public record MediumId(
            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medium_seq")
            @SequenceGenerator(name = "mediumSeq", sequenceName = "medium_seq", allocationSize = 1)
            @NotNull
            Long id) {}
}
