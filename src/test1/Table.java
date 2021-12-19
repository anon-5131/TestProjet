package test1;

import java.io.Serializable;
import java.util.Set;

public class Table implements Serializable {
    private int numero;
    private int nbPlaceLibre;
    private Set<Participant> PersonneAffectees;


    public int getNbPlaceLibre() {
        return nbPlaceLibre;
    }
}
