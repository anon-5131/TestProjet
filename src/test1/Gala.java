package test1;

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
    public static final int NB_TABLES_ETU = 15 ; // 11 à 25
    public static final int NB_TABLES_PERS = 10 ; // 1 à 10
    private Set<Personnel> lePersonnel;
    private Set<Etudiant> lesEtudiants;
    private List<Table> lesTables; // 0 --> etudiant non validié | personnel 1 à 10 | etudiant 11 à 25
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
        for (int i=0; i<=NB_TABLES_PERS+NB_TABLES_ETU;i++){
            lesTables.add(new Table());
        }
        lePersonnelInscrit=new HashSet<>();
        lesEtudiantsInscrit=new HashSet<>();
        lesReservation=new HashSet<>();
        lesReservationEnAttente=new HashSet<>();
        fileDAttente = new PriorityQueue<>();

    }

    /**
     * renvoi si le type,nom et num correspont à une personne dans la table
     * lePersonnel ou lesEtudiants
     * @param type "personnel" ou "etudiant"
     * @param num numero cherché
     * @return vrai si il existe dans lePersonnel ou lesEtudiants sinon non
     */
    public boolean checkIdentification(String type, int num) {
        if ("personnel".equals(type)) {
            for (Personnel personnel : lePersonnel){
                if(personnel.getNumeroPersonnel() == num ){
                    return true;
                }
            }
            return false;
        } else { // ("etudiant".equals(type))
            for (Etudiant etudiant : lesEtudiants){
                if(etudiant.getNumeroEtudiant() == num ){
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
     * cherche dans tous les set correspondant si il y a une reservation avec un
     * possesseur egale à l'utilisateur passé en paramètre
     * @param utilisateur l'utilisateur qui on veut retrouver sa reservation si il en possède
     * @return la reservation si elle existe sinon null
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
            if ( numero>=numerosLimites[0] && numero<=numerosLimites[1]){
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
        return null; // n'arrive jamais parce qu'on à verifier que le nombre est compris entre les bons nombres dans l'ihm
    }

    /**
     * Retourne le nombre de place maximum qu'un étudiant peut reserver en fonction
     * de ses années de formation
     * // @param etudiant etudiant dont on veut savoir le nombre max de place qui peut
     *                 reserver
     * @return 4 si 5ème années sinon 2
     */
    public int nbrPlaceMax(Etudiant etudiant) {
        if(etudiant.getAnneeDeFormation()==5){
            return 4;
        }else{
            return 2;
        }
    }

    /**
     * retourne le participant en fonction de son numero et son type
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
        return null; // n'arrive jamais parce qu'on à déjà vérifier si il existait avec checkIdentification
    }

    /**
     * Créer une nouvelle reservation et l'associe à lesReservation
     * et associe cette reservation à la table passée en paramètre
     * @param numero de la table correspondant à cette reservation
     * @param nombreDePlace de place de la reservation (>1 -> accompagné)
     * @param participant utilisateur qui fait la réservation
     */
    public void ajouterReservation(int numero, int nombreDePlace, Participant participant){
        Reservation nouvelleReservation = new Reservation(numero,nombreDePlace,participant);
        if (participant instanceof Etudiant) {
            lesReservationEnAttente.remove(nouvelleReservation);
        }
        lesReservation.add(nouvelleReservation);
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
        int nombrePlaceSupplementaire=0;
        while (!(fileDAttente.isEmpty())){
            nombrePlaceSupplementaire+=fileDAttente.peek().getNbDePlace();
            if (nombrePlaceSupplementaire<nombrePlaceAAjouter){
                lesReservationEnAttente.add(fileDAttente.poll());
            }else{
                break;
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
        int[] numerosLimites = recupererNumerosLimites(new Etudiant(0,null,null,null,null,0)); // créer un étudiant juste pour qu'on puisse recuperer les numeros correspondant aux etudiants
        for (Table table : lesTables){
            int numero = table.getNumero();
            if(numero>=numerosLimites[0] && numero<=numerosLimites[1]){
                valeurDeRetour+=Gala.NB_PLACES_TABLE-table.getNbPlaceLibre();
            }
        }
        return valeurDeRetour;
    }


    /**
     * Créer un string du contenu de toutes les conteneurs en utilisant toString
     * de tous les éléments
     * @return contenu des conteneurs
     */
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

    /**
     * Supprime toutes la reservation qui appartient à l'utilisateur dans toutes les conteneurs qui le concerne
     * si l'utilisateur n'a aucune reservation, ne fait rien.
     * @param utilisateur l'utilisateur qui possede la reservation
     */
    public void supprimerReservation(Participant utilisateur) {
        boolean supprimee = false;
        for (Reservation reservation : lesReservation) {
            if (reservation.getPossesseur().equals(utilisateur)) {
                lesReservation.remove(reservation);
                lesTables.get(reservation.getNumeroTable()).supprimerReservation(reservation);
                supprimee=true;
                break;
            }
        }
        if (utilisateur instanceof Etudiant){
            if (!supprimee){
                for (Reservation reservationEnAttente : lesReservationEnAttente) {
                    if (reservationEnAttente.getPossesseur().equals(utilisateur)) {
                        lesReservationEnAttente.remove(reservationEnAttente);
                        lesTables.get(reservationEnAttente.getNumeroTable()).supprimerReservation(reservationEnAttente);
                        supprimee=true;
                        break;
                    }
                }
            }
            if(!supprimee){
                for (Reservation reservationFileDattente : fileDAttente) {
                    if (reservationFileDattente.getPossesseur().equals(utilisateur)) {
                        fileDAttente.remove(reservationFileDattente);
                        lesTables.get(reservationFileDattente.getNumeroTable()).supprimerReservation(reservationFileDattente);
                        supprimee=true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Supprime de lesEtudiantsInscrits ou lePersonnelInscrit
     * l'utilisateur passé en paramètre
     * @param utilisateur que l'on veut supprimer des inscrits
     */
    public void desinscription(Participant utilisateur) {
        if(utilisateur instanceof Etudiant){
            lesEtudiantsInscrit.remove(utilisateur);
        }
        else if (utilisateur instanceof Personnel){
            lePersonnelInscrit.remove(utilisateur);
        }
    }

    /**
     * Test pour chaque table si il y a asser de place et s'il y en a assez
     * créer une nouvelle reservation à cette table
     * @param utilisateur
     * @param nombreDePlace
     * @throws Exception
     */
    public void reservationAutomatique(Participant utilisateur, int nombreDePlace) throws Exception {
        boolean ajoutEffectuer=false;
        int[] numerosLimites= recupererNumerosLimites(utilisateur);
        for (Table table : lesTables){
            int numero = table.getNumero();// int numero = table.getNumero();
            if (numero>=numerosLimites[0] && numero<=numerosLimites[1]){
                if (table.getNbPlaceLibre()>=nombreDePlace){
                    ajouterReservation(numero,nombreDePlace,utilisateur);
                    ajoutEffectuer=true;
                    break;
                }
            }
        }
        if(!ajoutEffectuer){
            throw new Exception("Echec de la création de la reservation :\nPlus assez de place dans les tables pour le nombre de places demandé");
        }
    }

    /**
     * retourne vrai si la reservation passée en paramètre est contenu dans lesReservationEnAttente
     * sinon renvoi faux
     * @param reservation que l'on veut tester
     * @return true si la reservation est dans lesReservationEnAttente
     */
    public boolean contientReservationEnAttente(Reservation reservation) {
        return lesReservationEnAttente.contains(reservation);
    }


    /**
     * Inscrit le participant (en le mettant dans lesEtudiantsInscrit ou lePersonnelInscrit)
     * @param utilisateur à inscrire
     */
    public void inscrire(Participant utilisateur) {
        if (utilisateur instanceof Personnel){
            lePersonnelInscrit.add((Personnel) utilisateur);
        }else{
            lesEtudiantsInscrit.add((Etudiant) utilisateur);
        }
    }

    /**
     * Créer une nouvelle reservation sans numero de table
     * @param nombreDePlace
     */
    public void reservationEtudiant(int nombreDePlace, Participant participant) {
        Reservation nouvelleReservation = new Reservation(0,nombreDePlace, participant);
        fileDAttente.add(nouvelleReservation);
        lesTables.get(0).ajouterReservation(nouvelleReservation);
    }
}
