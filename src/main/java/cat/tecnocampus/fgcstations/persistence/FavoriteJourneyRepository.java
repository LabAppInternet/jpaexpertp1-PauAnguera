package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.FavoriteJourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteJourneyRepository extends JpaRepository<FavoriteJourney, String> {
    List<FavoriteJourney> findByUserId(String user);
}
