package ci.gs2e.Gestion_Incidents.Controller;

import ci.gs2e.Gestion_Incidents.Modele.Logiciel;
import ci.gs2e.Gestion_Incidents.Modele.Environnement;
import ci.gs2e.Gestion_Incidents.Service.Environnement.EnvironnementService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/environnement")
//@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders="*")
public class EnvironnementController {

    @Autowired
   private EnvironnementService environnementService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity <List<Environnement>> getAll(){
        return new ResponseEntity<>(environnementService.listAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Environnement> create(@RequestBody Environnement env){

        return new ResponseEntity<Environnement>(environnementService.create(env), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Environnement> edit(@RequestBody Environnement env){

        return new ResponseEntity<Environnement>(environnementService.edit(env), HttpStatus.OK);
    }
    @DeleteMapping("/{idEnv}")
    public void delete(@PathVariable("idEnv") int idEnv){
        environnementService.delete(idEnv);

    }

    @GetMapping("/recherche")
    public  ResponseEntity<Optional<Environnement>> search(@RequestParam(name = "keyword") String libelleEnv){
        return  new ResponseEntity<Optional<Environnement>>(environnementService.listByLibelle(libelleEnv),HttpStatus.OK);
    }

}
