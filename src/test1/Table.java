package test1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Table implements Serializable {
    private int numero;
    private int nbPlaceLibre;
    private Set<Participant> PersonneAffectees;
    private static int numeroSuivant=1;

    public Table (){
        numero=++numeroSuivant;
        nbPlaceLibre=Gala.NB_PLACES_TABLE;
        PersonneAffectees = new HashSet<>();

    }


    public int getNbPlaceLibre() {
        return nbPlaceLibre;
    }
}
