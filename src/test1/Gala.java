package test1;

import java.io.Serializable;
import java.util.HashSet;
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
        lePersonnel=new HashSet<>(); // Pas du tout sûr
        lesEtudiants=new HashSet<>(); // Pas du tout sûr
        lesTables=new HashSet<>(); // Pas du tout sûr
        lePersonnelInscrit=new HashSet<>(); // Pas du tout sûr
        lesEtudiantsInscrit=new HashSet<>(); // Pas du tout sûr
        lesReservation=new HashSet<>(); // Pas du tout sûr
        lesReservationPersonnel=new HashSet<>(); // Pas du tout sûr
        fileDAttente = new PriorityQueue<>(); // Pas du tout sûr

        lesEtudiants.add(new Etudiant(2165001,"MARTIN","ADAM","638412609","adam.martin@etu-ec.fr",1)); // pour faire des test
        lesEtudiantsInscrit.add(new Etudiant(2165001,"MARTIN","ADAM","638412609","adam.martin@etu-ec.fr",1));
        lePersonnel.add(new Personnel(5110,"ALBERTIER","VINCENT","645202213","vincent.albertier@ec.fr"));

    }

    public boolean checkIdentification(String type, String nom, int num) {
        if ("personnel".equals(type)) {
            // return lePersonnel.contains(new Personnel(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Personnel personnel : lePersonnel){
                if(personnel.equals(new Personnel(num,nom))){
                    return true;
                }
            }
            return false;
        } else { // ("etudiant".equals(type))
            // return lesEtudiants.contains(new Etudiant(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Etudiant etudiant : lesEtudiants){
                if(etudiant.equals(new Etudiant(num,nom))){
                    return true;
                }
            }
            return false;
        }
    }

    public boolean checkInscrit(String type, String nom, int num){
        if ("personnel".equals(type)) {
           // return lePersonnelInscrit.contains(new Personnel(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Personnel personnel : lePersonnelInscrit){
                if(personnel.equals(new Personnel(num,nom))){
                    return true;
                }
            }
            return false;
        } else { // (type=="etudiant")
           // return lesEtudiantsInscrit.contains(new Etudiant(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Etudiant etudiant : lesEtudiantsInscrit){
                if(etudiant.equals(new Etudiant(num,nom))){
                    return true;
                }
            }
            return false;
        }
    }
}
