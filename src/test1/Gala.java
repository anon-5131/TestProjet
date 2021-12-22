package test1;

import java.io.Serializable;
import java.util.*;

/**
 * Classe de ouf
 */
public class Gala implements Serializable {
    public static final int TARIF1 = 10;
    public static final int TARIF2 = 15;
    public static final int TARIF3 = 20;
    public static final int NB_PLACES_TABLE = 8;
    public final int NB_TABLES_ETU = 15 ; // 11 à 25
    public final int NB_TABLES_PERS = 10 ; // 1 à 10
    private Set<Personnel> lePersonnel;
    private Set<Etudiant> lesEtudiants;
    private List<Table> lesTables; // 0->inutiliser|personnel 1 à 10 | etudiant 11 à 25
    private Set<Personnel> lePersonnelInscrit;
    private Set<Etudiant> lesEtudiantsInscrit;
    private Set<Reservation> lesReservation; // si dans ce set --> place reservé
    private Set<Reservation> lesReservationEnAttente; // si dans ce set --> place en attente de confirmation
    private PriorityQueue<Reservation> fileDAttente;
    
    public Gala() {
        lePersonnel=new HashSet<>(); // Pas du tout sûr
        lesEtudiants=new HashSet<>(); // Pas du tout sûr
        lesTables=new ArrayList<>(); // Pas du tout sûr
        for (int i=0; i<26;i++){
            lesTables.add(new Table());
        }
        lePersonnelInscrit=new HashSet<>(); // Pas du tout sûr
        lesEtudiantsInscrit=new HashSet<>(); // Pas du tout sûr
        lesReservation=new HashSet<>(); // Pas du tout sûr
        lesReservationEnAttente=new HashSet<>(); // Pas du tout sûr
        fileDAttente = new PriorityQueue<>(); // Pas du tout sûr

        lesEtudiants.add(new Etudiant(2165001,"MARTIN","ADAM","638412609","adam.martin@etu-ec.fr",1)); // pour faire des test
        lesEtudiantsInscrit.add(new Etudiant(2165001,"MARTIN","ADAM","638412609","adam.martin@etu-ec.fr",1));
        lePersonnel.add(new Personnel(5110,"ALBERTIER","VINCENT","645202213","vincent.albertier@ec.fr"));

    }

    public boolean checkIdentification(String type, String nom, int num) {
        if ("personnel".equals(type)) {
            // return lePersonnel.contains(new Personnel(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Personnel personnel : lePersonnel){
                if(personnel.getNumeroPersonnel() == num && nom.equals(personnel.getNom())){
                    return true;
                }
            }
            return false;
        } else { // ("etudiant".equals(type))
            // return lesEtudiants.contains(new Etudiant(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Etudiant etudiant : lesEtudiants){
                if(etudiant.getNumeroEtudiant() == num && nom.equals(etudiant.getNom())){
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
                if(personnel.getNumeroPersonnel() == num && nom.equals(personnel.getNom())){
                    return true;
                }
            }
            return false;
        } else { // (type=="etudiant")
           // return lesEtudiantsInscrit.contains(new Etudiant(num, nom)); ça marche pas // redefinir pour que le equals fonctionne
            for (Etudiant etudiant : lesEtudiantsInscrit){
                if(etudiant.getNumeroEtudiant() == num && nom.equals(etudiant.getNom())){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * cherche dans tous les set correspondant si il y a une reservation au numero donné et renvoi le nom du set qui la contient sinon "aucune"
     * @param typeUtilisateur si typeUtilisateur == Personnel cherche dans lesReservation.
     *                        si typeUtilisateur == Etudiant cherche dans lesReservation, lesReservationEnAttente et fileDAttente.
     * @param numeroUtilisateur numero pour savoir si le
     * @return si dans lesReservation->"reservation" || lesReservationEnAttente->"enAttente" || fileDAttente->"fileDAttente" || si dans aucune->"aucune"
     */
    //public String retrouverReservation(String typeUtilisateur,int numeroUtilisateur){
    //}


    /**
     * retourne la composition des table en utilisant un foreach et le tostring des tables
     * @param typeParticipant cherche soit dans les table 1 à 10 (personnel) ou 11 à 25 (etudiant)
     * @return affichage composition des tables
     */
    public String toStringLesTables(String typeParticipant) {
        int numeroMini=0; // Comment on fait ?
        int numeroMaxi=0; // Comment on fait ?
        String valeurDeRetour="";
        if ("personnel".equals(typeParticipant)){
            numeroMini=1;//int numeroMini=1;
            numeroMaxi=NB_TABLES_PERS;//int numeroMaxi=NB_TABLES_PERS;
        }else{
            numeroMini=NB_TABLES_PERS+1;//int numeroMini=NB_TABLES_PERS+1;
            numeroMaxi=NB_TABLES_PERS+NB_TABLES_ETU;//int numeroMaxi=NB_TABLES_PERS+NB_TABLES_ETU;
        }
        for (Table table : lesTables){
            int numero = table.getNumero();
            if ( numero>=numeroMini && numero<=numeroMaxi){ // PK ça marche pas ?
                valeurDeRetour+=table.toString();
            }
        }
        return valeurDeRetour;
    }


    /**
     * retourne la table correspondant au numero de la table
     * @param numTable numero de la table chercher
     * @return table que l'on cherche
     */
    //public Table retrouverTable(int numTable) {
    //}


    /**
     * retrourne la reservation correspondant au numero d'utilisateur dans le Set lesReservation
     * @param numeroUtilisateur de la personne qui possede la reservation
     * @return la reservation correspondate
     */
    //public Reservation getReservationLesReservation(int numeroUtilisateur){
    //}


    /**
     * retrourne la reservation correspondant au numero d'utilisateur dans la PriorityQueue fileDAttente
     * @param numeroUtilisateur de la personne qui possede la reservation
     * @return la reservation correspondate
     */
    //public Reservation getReservationFileDAttente(int numeroUtilisateur) {
    //}


    /**
     * retrourne la reservation correspondant au numero d'utilisateur dans le Set lesReservationEnAttente
     * @param numeroUtilisateur de la personne qui possede la reservation
     * @return la reservation correspondate
     */
    //public Reservation getReservationEnAttente(int numeroUtilisateur) {
    //}


    /**
     * Retourne l'étudiant qui possede le numeroUtilisateur comme numero d'étudiant
     * @param numeroUtilisateur numero qu'on cherche
     * @return etudiant rechercher
     */
    //public Object getEtudiant(int numeroUtilisateur) {
    //}


    /**
     * Retourne le nombre de place maximum qu'un étudiant peut reserver en fonction
     * de ses années de formation
     * // @param etudiant etudiant dont on veut savoir le nombre max de place qui peut
     *                 reserver
     * @return 3 si 5ème années sinon 2
     */
    //public int nbrPlaceMax(Object etudiant) {
    //}

    public List<Table> getLesTables() {
        return lesTables;
    }

    public Participant getParticipant(String typeParticipant, int numeroUtilisateur) {
        if ("personnel".equals(typeParticipant)){
            for (Personnel personnel : lePersonnel){
                if (personnel.getNumeroPersonnel() == numeroUtilisateur){
                    return personnel;
                }
            }
        }else{
            for (Etudiant etudiant : lesEtudiants){
                if(etudiant.getNumeroEtudiant() == numeroUtilisateur){
                    return etudiant;
                }
            }
        }
        return null; // comment on fait ?
    }

    public void ajouterReservation(int numero, int nombreDePlace, Participant participant, String typeParticipant){
        Reservation nouvelleReservation = new Reservation(numero,nombreDePlace,participant);
        if ("personnel".equals(typeParticipant)){
            lesReservation.add(nouvelleReservation);
        }else{
            fileDAttente.add(nouvelleReservation);
        }
        lesTables.get(numero).ajouterReservation(nouvelleReservation);
    }
}
