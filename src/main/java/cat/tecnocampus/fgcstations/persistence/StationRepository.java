package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Friends;
import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, String> {
    Station findByNom(String nom);
}
