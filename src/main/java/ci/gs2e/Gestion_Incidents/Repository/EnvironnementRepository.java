package ci.gs2e.Gestion_Incidents.Repository;

import ci.gs2e.Gestion_Incidents.Modele.Environnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvironnementRepository extends JpaRepository<Environnement,Integer> {
    Optional<Environnement> findByLibelleEnvContains(String libelleEnv);
}
