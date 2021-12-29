package test1.model;

import java.io.File;
import java.io.FileNotFoundException;
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

    public Gala() throws Exception { // Constructeur pour la premiere utilisation
        try {
            Scanner lectureEtudiant = new Scanner(new File("etudiants.txt"));
            lesEtudiants=new HashSet<>();
            while(lectureEtudiant.hasNext()){
                lesEtudiants.add(new Etudiant(lectureEtudiant.nextInt(),lectureEtudiant.next(),lectureEtudiant.next(),lectureEtudiant.next(),lectureEtudiant.next(),lectureEtudiant.nextInt()));
            }
            Scanner lecturePersonnel = new Scanner (new File("personnel.txt"));
            lePersonnel=new HashSet<>();
            while(lecturePersonnel.hasNext()){
                lePersonnel.add(new Personnel(lecturePersonnel.nextInt(),lecturePersonnel.next(),lecturePersonnel.next(),lecturePersonnel.next(),lecturePersonnel.next()));
            }
            } catch (FileNotFoundException e) {
            throw new Exception("Veuiller mettre le fichier etudiants.txt et personnel.txt dans à la racine du programme");
        }
        lesTables=new ArrayList<>();
        for (int i=0; i<26;i++){
            lesTables.add(new Table());
        }
        lePersonnelInscrit=new HashSet<>(); // Pas du tout sûr
        lesEtudiantsInscrit=new HashSet<>(); // Pas du tout sûr
        lesReservation=new HashSet<>(); // Pas du tout sûr
        lesReservationEnAttente=new HashSet<>(); // Pas du tout sûr
        fileDAttente = new PriorityQueue<>(); // Pas du tout sûr

    }

    /**
     * renvoi si le type,nom et num correspont à une personne dans la table
     * lePersonnel ou lesEtudiants
     * @param type "personnel" ou "etudiant"
     * @param nom nom cherch"
     * @param num numero cherché
     * @return vrai si il existe dans lePersonnel ou lesEtudiants sinon non
     */
    public boolean checkIdentification(String type, String nom, int num) {
        if ("personnel".equals(type)) {
            for (Personnel personnel : lePersonnel){
                if(personnel.getNumeroPersonnel() == num && nom.equals(personnel.getNom())){
                    return true;
                }
            }
            return false;
        } else { // ("etudiant".equals(type))
            for (Etudiant etudiant : lesEtudiants){
                if(etudiant.getNumeroEtudiant() == num && nom.equals(etudiant.getNom())){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * renvoi si l'utilisateur est déjà inscrit à une personne dans la table
     * lePersonnelInscrit ou lesEtudiantsInscrit
     * @param utilisateur
     * @return vrai si il existe dans lePersonnelInscrit ou lesEtudiantsInscrit sinon non
     */
    public boolean checkInscrit(Participant utilisateur){
        if (utilisateur instanceof Personnel) {
            return lePersonnelInscrit.contains(utilisateur);
        }else if (utilisateur instanceof Etudiant){
            return lesEtudiantsInscrit.contains(utilisateur);
        }
        return false;
    }

    /**
     * cherche dans tous les set correspondant si il y a une reservation au numero donné et renvoi le nom du set qui la contient sinon "aucune"
     * @param utilisateur l'utilisateur qui on veut retrouver sa reservation si il en possède
     * @return si dans lesReservation->"reservation" || lesReservationEnAttente->"enAttente" || fileDAttente->"fileDAttente" || si dans aucune->"aucune"
     */
    public Reservation retrouverReservation(Participant utilisateur) {

        for (Reservation reservation : lesReservation) {
            if (reservation.getPossesseur().equals(utilisateur)) {
                return reservation;
            }
        }

        if (utilisateur instanceof Etudiant) {
            for (Reservation reservationEnAttente : lesReservationEnAttente) {
                if (reservationEnAttente.getPossesseur().equals(utilisateur)) {
                    return reservationEnAttente;
                }
            }
            for (Reservation fileDattente : fileDAttente) {
                if (fileDattente.getPossesseur().equals(utilisateur)) {
                    return fileDattente;
                }
            }
        }return null;
    }


    /**
     * retourne la composition des table en utilisant un foreach et le tostring des tables
     * @param utilisateur en fonction de son type cherche soit dans les table
     *                   1 à 10 (personnel) ou 11 à 25 (etudiant)
     * @return affichage composition des tables
     */
    public String toStringLesTables(Participant utilisateur) {
        int[] numerosLimites=recupererNumerosLimites(utilisateur);
        String valeurDeRetour="";
        for (Table table : lesTables){
            int numero = table.getNumero();
            if ( numero>=numerosLimites[0] && numero<=numerosLimites[1]){ // PK ça marche pas ?
                valeurDeRetour+=table.toString();
            }
        }
        return valeurDeRetour;
    }

    /**
     * retourne le numero mini et numero maxi en fonction du type de participant
     * @param utilisateur en fonction de son type
     *                   choisis entre 1 et 10 (personnel)  et 11 et 25 (etudiant)
     * @return les nuemros limites de sorte que numeroMini=valeurDeRetour[0] numeroMaxi=valeurDeRetour[1]
     */
    private int[] recupererNumerosLimites(Participant utilisateur) {
        int[] valeurDeRetour = new int[2];
        if (utilisateur instanceof Personnel){
            valeurDeRetour[0]=1;
            valeurDeRetour[1]=NB_TABLES_PERS;
        }else{
            valeurDeRetour[0]=NB_TABLES_PERS+1;
            valeurDeRetour[1]=NB_TABLES_PERS+NB_TABLES_ETU;
        }
        return valeurDeRetour;
    }


    /**
     * retourne la table correspondant au numero de la table
     * @param numTable numero de la table chercher
     * @return table que l'on cherche
     */
    public Table retrouverTable(int numTable) {
        for (Table table : lesTables){
            if(numTable == table.getNumero()){
                return table;
            }
        }
        return null; // n'arrive jamais parce qu'on à verifier que le nombre est compris entre les bons nombres
    }


    /**
     * retrourne la reservation correspondant au numero d'utilisateur dans le Set lesReservation
     * @param numeroUtilisateur de la personne qui possede la reservation
     * @return la reservation correspondate
     */
    //public Reservation getReservationLesReservation(int numeroUtilisateur){
      //  return null;}


    /**
     * retrourne la reservation correspondant au numero d'utilisateur dans la PriorityQueue fileDAttente
     * @param numeroUtilisateur de la personne qui possede la reservation
     * @return la reservation correspondate
     */
    //public Reservation getReservationFileDAttente(int numeroUtilisateur) {
      //  return null;}


    /**
     * retrourne la reservation correspondant au numero d'utilisateur dans le Set lesReservationEnAttente
     * @param numeroUtilisateur de la personne qui possede la reservation
     * @return la reservation correspondate
     */
    //public Reservation getReservationEnAttente(int numeroUtilisateur) {
      //  return null;}


    /**
     * Retourne l'étudiant qui possede le numeroUtilisateur comme numero d'étudiant
     * dans le set lesEtudiants
     * @param numeroUtilisateur numero qu'on cherche
     * @return etudiant rechercher
     */
    public Etudiant getEtudiant(int numeroUtilisateur) {
        for (Etudiant etudiant : lesEtudiants){
            if (etudiant.getNumeroEtudiant() == numeroUtilisateur){
                return etudiant;
            }
        }
        System.out.println("Etudiant introuvable avec ce numero Utilisateur");
        return null;
    }


    /**
     * Retourne le nombre de place maximum qu'un étudiant peut reserver en fonction
     * de ses années de formation
     * // @param etudiant etudiant dont on veut savoir le nombre max de place qui peut
     *                 reserver
     * @return 3 si 5ème années sinon 2
     */
    public int nbrPlaceMax(Etudiant etudiant) {
        if(etudiant.getAnneeDeFormation()==5){
            return 3;
        }else{
            return 2;
        }
    }

    public List<Table> getLesTables() {
        return lesTables;
    }

    /**
     * retourn le participant en fonction de son numero et son type
     * @param typeParticipant cherché
     * @param numeroUtilisateur cherché
     * @return participant cherché
     */
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

    /**
     * Créer une nouvelle reservation et l'associe à lesReservation (personnel) ou
     * fileDAttente (etudiant) et associe cette reservation à la table passée en paramètre
     * @param numero de l'utilisateur à ajouter
     * @param nombreDePlace de place de la reservation (>1 -> accompagné)
     * @param participant utilisateur qui fait la réservation
     */
    public void ajouterReservation(int numero, int nombreDePlace, Participant participant){
        Reservation nouvelleReservation = new Reservation(numero,nombreDePlace,participant);
        if (participant instanceof Personnel){
            lesReservation.add(nouvelleReservation);
        }else{
            fileDAttente.add(nouvelleReservation);
        }
        lesTables.get(numero).ajouterReservation(nouvelleReservation);
    }

    /**
    * Mais à jour le set des reservation dont la réservation est en attente
    * en fonction du nombre total de place et du nombre déjà en attente.
    */

    public void miseAJourReservationAttente() {
        int nombrePlaceOccupees=retrouverNombrePlacesOccupeesEtudiant();
        int nombrePlaceLibre=NB_TABLES_ETU*NB_PLACES_TABLE-nombrePlaceOccupees;
        int nombrePlaceAAjouter=nombrePlaceLibre-lesReservationEnAttente.size();
        if(!(fileDAttente.isEmpty())){
            for (int i=0; i<nombrePlaceAAjouter; i++){
                if (!fileDAttente.isEmpty()){
                    lesReservationEnAttente.add(fileDAttente.poll());
                }
            }
        }
    }

    /**
     * Retourne le nombre de place que les étudiant occupe dans les tables correspondante
     * (en comptant les acompagnateurs
     * @return nombre de place occupée par les étudiant
     */
    private int retrouverNombrePlacesOccupeesEtudiant() {
        int valeurDeRetour=0;
        int[] numerosLimites = recupererNumerosLimites(new Etudiant(0,null,null,null,null,0)); // créer un étudiant juste pour qu'on pouisse recuperer les numeros correspondant aux etudians
        for (Table table : lesTables){
            int numero = table.getNumero();
            if(numero>=numerosLimites[0] && numero<=numerosLimites[1]){
                valeurDeRetour+=Gala.NB_PLACES_TABLE-table.getNbPlaceLibre();
            }
        }
        return valeurDeRetour;
    }


    @Override
    public String toString() {
        String valeurDeRetour = "";
        valeurDeRetour+="Contenu de \"lePersonnel\"\n";
        for (Personnel personnel : lePersonnel){
            valeurDeRetour+=personnel.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"lesEtudiants\"\n";
        for(Etudiant etudiant : lesEtudiants){
            valeurDeRetour+=etudiant.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"lesTables\"\n";
        for(Table table : lesTables){
            valeurDeRetour+=table.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"lePersonnelInscrit\"\n";
        for (Personnel personnel : lePersonnelInscrit){
            valeurDeRetour+=personnel.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"lesEtudiantsInscrit\"\n";
        for(Etudiant etudiant : lesEtudiantsInscrit){
            valeurDeRetour+=etudiant.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"lesReservation\"\n";
        for(Reservation reservation : lesReservation){
            valeurDeRetour+= reservation.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"lesReservationEnAttente\"\n";
        for(Reservation reservation : lesReservationEnAttente){
            valeurDeRetour+= reservation.toString()+"\n";
        }
        valeurDeRetour+="Contenu de \"fileDAttente\"\n";
        for(Reservation reservation : fileDAttente){
            valeurDeRetour+= reservation.toString()+"\n";
        }
        return valeurDeRetour;
    }

    public void supprimerReservation(Reservation reservation) {
        for (Reservation lesReservation : lesReservation) {
            if (lesReservation.equals(reservation)) {
                this.lesReservation.remove(reservation);
                lesTables.get(reservation.getNumeroTable()).supprimerReservation(reservation);
            }
        }
        for (Reservation reservationEnAttente : lesReservationEnAttente) {
            if (reservationEnAttente.equals(reservation)) {
                this.lesReservationEnAttente.remove(reservation);
                lesTables.get(reservation.getNumeroTable()).supprimerReservation(reservation);
            }
        }
        for (Reservation fileDattente : fileDAttente) {
            if (fileDattente.equals(reservation)) {
                this.fileDAttente.remove(reservation);
                lesTables.get(reservation.getNumeroTable()).supprimerReservation(reservation);
            }
        }
    }

    public void desinscription(Participant utilisateur) {
        if(utilisateur instanceof Etudiant){
            lesEtudiantsInscrit.remove(utilisateur);
        }
        else if (utilisateur instanceof Personnel){
            lePersonnelInscrit.remove(utilisateur);
        }
    }
    public Personnel getPersonnel(int numeroUtilisateur) {
        for (Personnel personnel : lePersonnel){
            if (personnel.getNumeroPersonnel() == numeroUtilisateur){
                return personnel;
            }
        }
        System.out.println("Personnel introuvable avec ce numero Utilisateur");
        return null;
    }

    public void reservationAutomatique(Participant utilisateur, int nombreDePlace) {
        int[] numerosLimites= recupererNumerosLimites(utilisateur);
        for (Table table : getLesTables()){
            int numero = table.getNumero();// int numero = table.getNumero();
            if (numero>=numerosLimites[0] && numero<=numerosLimites[1]){
                if (table.getNbPlaceLibre()>=nombreDePlace){
                    ajouterReservation(numero,nombreDePlace,utilisateur);
                    break;
                }
            }
        }
    }

    public boolean contientReservationEnAttente(Reservation reservation) {
        return lesReservation.contains(reservation);
    }

    public void inscrire(Participant utilisateur) {
        if (utilisateur instanceof Personnel){
            lePersonnelInscrit.add((Personnel) utilisateur);
        }else{
            lesEtudiantsInscrit.add((Etudiant) utilisateur);
        }
    }
}
