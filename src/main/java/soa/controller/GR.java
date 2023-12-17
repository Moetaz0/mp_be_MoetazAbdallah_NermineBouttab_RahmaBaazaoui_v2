package soa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.entities.Clients;
import soa.entities.Facture;
import soa.entities.Reglement;
import soa.metier.FactureMetierImpl;
import soa.metier.IFactureMetierImpl;
import soa.repository.ClientRepo;
import soa.repository.FactureR;
import soa.repository.ReglementRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gr")
public class GR {

    @Autowired
    private ReglementRepository R;

    @Autowired
    private FactureR F;
    @Autowired
    private ClientRepo ClientRep;

    private IFactureMetierImpl factureMetier;
    private  Facture facture;

    public GR() {
        this.factureMetier = new IFactureMetierImpl() {
            @Override
            public boolean checkStatus() {
                return false;
            }
        };
    }

    @GetMapping(value = "reglement/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Reglement> getAll() {
        return R.findAll();
    }

    @GetMapping(value = "reglement/get/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Reglement getReglement(@PathVariable Long id) {
        return R.findById(id).orElse(null);
    }


    @DeleteMapping(value = "reglement/delete/{id}")
    public ResponseEntity<String> deleteReglement(@PathVariable Long id) {
        try {
            R.deleteById(id);
            return new ResponseEntity<>("Reglement with ID " + id + " deleted successfully", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            // This exception is thrown if the entity with the specified ID is not found
            return new ResponseEntity<>("Reglement with ID " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connectivity issues)
            return new ResponseEntity<>("Error deleting Reglement with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "reglement/add", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
            ,produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Reglement saveReglement(@RequestBody Reglement r) {
        try {
            Optional<Facture> facture =F.findById(r.getIdFacture());
            r.setIdFacture(facture.get().getIdFacture());
            return R.save(r);
        }
        catch (Exception e) {
            // Log the exception
            e.printStackTrace();

        }
        return r;
    }


    @GetMapping(value = "Clients/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Clients> getAllClients() {
        checkStatusAndCash();
        return ClientRep.findAll();
    }

    private void checkStatusAndCash() {
        List<Facture> factures = F.findAll();


        for ( Facture facture:factures)
        {
            double reste = facture.getMpFacture();
            for (Reglement reglement : facture.getReglementList()) {
                reste -= reglement.getMp();
            }
            reste = Math.max(0, reste);
            if (reste == 0) {
                facture.setStatus(true);
            } else if (reste > 0) {
                facture.setStatus(false);
            }
            facture.setMpFacture(reste);
            F.save(facture);
        }
    }


    @GetMapping(value = "reglement/getByNum/{num}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Reglement> getReglementsByFactureNum(@PathVariable int num) {
        return F.findReglementListByNum(num);
    }
    @GetMapping(value = "reglement/getFactureByNum/{num}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Facture> getFactureNum(@PathVariable int num) {
        return F.findFactureByNum(num);
    }
    @GetMapping(value = "reglement/getFactures", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Facture> getFactures() {
        return F.findAll();
    }

}
