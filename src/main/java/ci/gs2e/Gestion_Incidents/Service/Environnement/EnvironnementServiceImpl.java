package ci.gs2e.Gestion_Incidents.Service.Environnement;

import ci.gs2e.Gestion_Incidents.Modele.Environnement;
import ci.gs2e.Gestion_Incidents.Repository.EnvironnementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class EnvironnementServiceImpl implements EnvironnementService{
    EnvironnementRepository environnementRepository;
    @Override
    public Environnement create(Environnement environnement)
    {
        return environnementRepository.save(environnement);
    }

    @Override
    public Environnement edit(Environnement environnement) {
        Environnement existingEnv = environnementRepository.findById(environnement.getIdEnv()).orElse(null);
        existingEnv.setLibelleEnv(environnement.getLibelleEnv());


        return environnementRepository.save(existingEnv);
    }

    @Override
    public void delete(int idEnv) {
        environnementRepository.deleteById(idEnv);

    }

    @Override
    public List<Environnement> listAll() {

        return environnementRepository.findAll();
    }

    @Override
    public Optional<Environnement> listByLibelle(String libelleEnv) {

        return environnementRepository.findByLibelleEnvContains(libelleEnv);
    }
}
