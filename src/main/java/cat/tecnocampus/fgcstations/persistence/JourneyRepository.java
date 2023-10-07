package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, String> {

    @Query("""
SELECT j.id FROM Journey j WHERE j.origin = :origin AND j.destination = :destination
""")
    String getJourneyId(@Param("origin") Station origin, @Param("destination") Station destination);

    Optional<Journey> findById(String id);
}
