package soa.metier;

import soa.entities.Facture;
import soa.repository.FactureR;

public class FactureMetierImpl implements IFactureMetierImpl {
    private FactureR factureRepo;
    private Facture facture;
    public boolean checkStatus()
    {
        boolean checking=false;
        if (facture.getMpFacture()==0)
        {
            checking=facture.setStatus(true);
        }
        else {
            checking=facture.setStatus(false);
        }


        return checking;
    }
}
