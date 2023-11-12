package ci.gs2e.Gestion_Incidents.Service.Environnement;

import ci.gs2e.Gestion_Incidents.Modele.Environnement;

import java.util.List;
import java.util.Optional;

public interface EnvironnementService {
    public Environnement create(Environnement environnement);
    public  Environnement edit(Environnement environnement);
    public void delete(int idEnv);
    public List<Environnement> listAll();
    public Optional<Environnement> listByLibelle(String libelleEnv);


}
