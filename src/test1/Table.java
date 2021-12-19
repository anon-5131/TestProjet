package test1;

import java.io.Serializable;
import java.util.List;

public class Table implements Serializable {
    private int numero;
    private int nbPlaceLibre;
    private List<Participant> listePersonneAffectees;


    public int getNbPlaceLibre() {
        return nbPlaceLibre;
    }
}
