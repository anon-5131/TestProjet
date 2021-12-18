package test1;

import java.io.Serializable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Classe de ouf
 */
public class Gala implements Serializable {
    public final int TARIF1 = 10;
    public final int TARIF2 = 15;
    public final int TARIF3 = 20;
    public final int NB_TABLES_ETU = 15 ; // 11 à 25
    public final int NB_TABLES_PERS = 10 ; // 1 à 10
    private Set<Personnel> lePersonnel;
    private Set<Etudiant> lesEtudiants;
    private Set<Table> lesTables;
    private Set<Personnel> lePersonnelInscrit;
    private Set<Etudiant> lesEtudiantsInscrit;
    private Set<Reservation> lesReservation; // si dans ce set --> place reservé
    private Set<Reservation> lesReservationPersonnel; // si dans ce set --> place en attente de confirmation
    private PriorityQueue<Reservation> fileDAttente;
    
    public Gala() {

    }

}
