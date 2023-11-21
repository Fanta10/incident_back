package ci.gs2e.Gestion_Incidents.Service.Application;

import ci.gs2e.Gestion_Incidents.Modele.Logiciel;
import ci.gs2e.Gestion_Incidents.Modele.Rex;
import ci.gs2e.Gestion_Incidents.Repository.AppEnvRepository;
import ci.gs2e.Gestion_Incidents.Repository.ApplicationRepository;
import ci.gs2e.Gestion_Incidents.Repository.RexRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
   private ApplicationRepository applicationRepository;

    @Autowired
    private RexRepository rexRepository;
    @Override
    public Logiciel create(Logiciel application) {

        return applicationRepository.save(application);
    }

    @Override
    public Logiciel edit(Logiciel application) {
     Logiciel existingApp = applicationRepository.findById(application.getIdApp()).orElse(null);
        Rex existingRex =rexRepository.findById(application.getRex().getIdRex()).orElse(null);
       existingApp.setLibelleApp(application.getLibelleApp());
       existingApp.setDescriptionApp(application.getDescriptionApp());
       existingRex.setIdRex(application.getRex().getIdRex());
       existingRex.setPrenom(application.getRex().getPrenom());
       existingRex.setNom(application.getRex().getNom());
       existingRex.setFonction(application.getRex().getFonction());
       //existingApp.setRex(application.getRex());
        existingApp.setRex(existingRex);

        return applicationRepository.save(existingApp);
    }

    @Override
    public void delete(int idApp) {
        applicationRepository.deleteById(idApp);

    }

    @Override
    public List<Logiciel> listAll() {

        return applicationRepository.findAll();
    }

    @Override
    public Logiciel listByLibelle(Logiciel application) {
        return null;
    }


}
