package test1;

import test1.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Controleur {
    private Ihm ihm;
    private Gala gala;
    private Participant utilisateur;

    public Controleur(LocalDate dateDuGala) throws Quitter {
        ihm = new Ihm();
        try{
            Scanner scanner =new Scanner(new File("gala.ser")); // regarder si le fichier gala.ser existe si non --> catch
            ServiceStockage serviceStockage = new ServiceStockage();
            gala=(Gala)serviceStockage.charger();
            System.out.println("bonjour");
        }catch(IOException | ClassNotFoundException e ){
            System.out.println(e.getMessage());
            try {
                gala = new Gala();
                System.out.println("bonsoir");
            }catch (Exception exception){
                ihm.message(exception.getMessage());
                throw new Quitter("Fermeture du programme");
            }
        }
        if (LocalDate.now().isAfter(dateDuGala.minusMonths(1))){
            ctlMiseAJourReservationAttente(); // après un mois fait avancer la file d'attente en fonction de la place et des désinscription
        }
        ihm.message(gala.toString()); // affiche l'état, les contenu des conteneurs d'objets
        ctlIdentification(); // pour remplir les attributs identifiantUtilisateur et typeParticipant, et obligé l'utilisateur à s'identifier

    }


    /**
     * Utilise la méthode identification de l'ihm pour récuperer
     * l'identifiant de l'utilisateur qui veux s'inscire
     * affecte la bonne valeur a l'attribut utilisateur
     */
    public void ctlIdentification (){
        boolean connecte = false;
        while (!connecte) {
            String typeParticipantTemporaire = ihm.identificationType();
            String nomUtilisateurTemporaire = ihm.identificationNom();
            int numUtilisateurTemporaire = ihm.identificationNum();

            if (gala.checkIdentification(typeParticipantTemporaire, nomUtilisateurTemporaire, numUtilisateurTemporaire)) {
                utilisateur = gala.getParticipant(typeParticipantTemporaire, numUtilisateurTemporaire);
                ihm.message("Votre identification est validé");
               connecte=true;
            } else {
                ihm.message("Vous êtes introuvable dans notre base de données, l'identification à échouer, veuillez recommencer");
            }
        }
    }


    /**
     * Permet de savoir si un utilisateur est déjà inscrit grâce au set de la classe
     * Gala (lePersonnelInscrit et lesEtudiantsInscrit)
     * Attention à séparer deux méthodes ou parties entre les etudiant et le personnel
     * @return vrai si le participant est inscrit sinon false
     */
    public boolean ctlUtilisateurInscrit(){
        return gala.checkInscrit(utilisateur);
    }

    /**
     * Inscrit un participant dans le set de la classe Gala correspondante (on a
     * deja tester si il était présent grace à ctlUtilisateurInscrit) (lePersonelInscrit
     * ou lesEtudiantsInscrit)
     */
    public void ctlInscription(){
        gala.inscrire(utilisateur);
    }

    /**
     * Utilise l'ihm pour proposer un menu :
     * 1 – Gérer les places du dîner
     * 2 – Se désinscrire
     * 3 – Quitter
     *
     * Il appeleront les methodes du controleur correspondante :
     * 1 - ctlGestionPlace()
     * 2 - ctlDesinscription() (si etudiant penser maj set reservation
     * en attente confitmation (faire avancer la file))
     * 3 - ctlQuitter()
     */

    public void ctlMenuGestionPlace() throws Quitter {
        int choix = ihm.menuGestionPlace();
        if(choix == 1){
            ctlgestionPlace();
        }else if(choix == 2){
            ctlDesinscription();
        }else if (choix == 3){
            ctlQuitter();
        }
        // On a verifier que choix peut prendre qu'une valeur entre 1 et 3 dans ihm.menuGestionPlace();
    }


    /**
     * Supprime toutes les demandes de reservation et ou les reservation
     * qui le concerne  puis, désinscrit un participant dans le set de la classe Gala correspondante
     * @throws Quitter quitte après être désinscrit
     */
    public void ctlDesinscription() throws Quitter {
        gala.supprimerReservation(utilisateur);
        gala.desinscription(utilisateur);
        ihm.message("Vous vous êtes bien desinscrit");
        ctlQuitter(); // quitte pour que l'utilisateur ne puisse pas reserver une table alors qu'il n'est plus inscrit
    }




    /**
     * Sauvegarde le gale puis lance l'exeption Quitter pour arreter l'application
     * @throws Quitter une exeption que l'on catch dans le main pour arreter l'application
     */
    public void ctlQuitter() throws Quitter {
        try {
            ServiceStockage serviceStockage= new ServiceStockage();
            serviceStockage.enregistrer(gala);
        } catch (IOException e) {
            throw new Quitter("Erreur dans la sauvegarde");
        }
        throw new Quitter("Fermeture du programme");
    }

    /**
     * Identifie si le participant est un Etudiant ou un membre du Personnel
     *
     * Si Personnel :
     *
     * Si l'utilisateur à déjà une reservation, affiche le nombre de place et
     * le numero de table
     *
     * Sinon
     * - Affiche le nombre de place max qui peut reserver (2)
     * - Demande s'il souhaite consulter le plan des tables Personnel(méthode de ihm)
     *
     *   - Si oui :
     *      - afficher le plan des tables (definir methode dans gala en
     * utilisant le toString de table
     *      - l'utilisateur choisit un numero de table (méthode de ihm)
     *      - l'utilisateur choisit un nombre de place si il n'y a pas assez de
     * place libre envoie un message et revient au debut de la fonction
     *
     *   - Si non :
     *      - l'utilisateur choisit un nombre de place et une table avec assez de
     * place libre lui sera attribuer
     * - Quitte l'application
     *
     *
     * Si Etudiant :
     *
     * si pas de demande de reservation :
     *  - indique le nombre de palce max (à partir de l'annee de formation)
     *  - demande combien de place
     *  - fait demande de reservation
     *
     * sinon si demande dans fil d'attente :
     *  - indique affiche le nombre de places demandées
     *
     * sinon si demande de reservation en attente d'acceptation :
     * - affiche le nombre de place demandées
     * - demande confirmation de la reservation
     *
     * (comme personnel)
     * - Demande s'il souhaite consulter le plan des tables Etudiant(méthode de ihm)
     *   - Si oui :
     *      - afficher le plan des tables (definir methode dans gala en
     * utilisant le toString de table
     *      - l'utilisateur choisit un numero de table
     *   - Si non :
     *      - affecte automatiquement uen table avec assez  de place
     * - Affiche le montant de la reservation
     * - Quitter l'application
     *
     * Si place déjà reservé :
     * - indique le nombre de places
     *
     *
     */

    public void ctlgestionPlace() throws Quitter {
        Reservation reservation=gala.retrouverReservation(utilisateur);
        if (utilisateur instanceof Personnel){
            if (reservation == null) {
                while (true) {
                    ihm.message("Vous pouvez reserver jusqu'à 2 places");
                    if(ihm.demandePlanDesTables()) {
                        ihm.message(gala.toStringLesTables(utilisateur));
                        int numTable = ihm.choixTable(utilisateur);
                        Table table = gala.retrouverTable(numTable);
                        ihm.message("vous avez choisit la table numero "+numTable+" : \n"+table); // toString de table qui montre la composition de la table choisit
                        int nombreDePlace=ihm.nbrPlace(2);
                        if(nombreDePlace <= table.getNbPlaceLibre()){
                            ctlReservation(numTable,nombreDePlace);
                            break;
                        }else{
                            ihm.message("vous avez demander un nombre de place invalide");
                        }

                    }else{
                        int nombreDePlace = ihm.nbrPlace(2);
                        ctlReservation(nombreDePlace);
                        break;
                    }
                }
            }// Si l'utilisateur est un membre du personnel et n'a pas de reservation
            else{
                ihm.message("Vous avez reserver "+reservation.getNbDePlace()+" place(s)\nVous êtes inscrit à la table numéro "+reservation.getNumeroTable()+".");
            }
        // Si l'utilisateur est un étudiant
        }else{
            if (reservation == null) {
                int nbrPlaceMax = gala.nbrPlaceMax((Etudiant) utilisateur);
                ihm.message("Vous pouvez reserver jusqu'à " + nbrPlaceMax + " place(s)");
                int nombreDePlace = ihm.nbrPlace(nbrPlaceMax);
                ctlReservation(nombreDePlace);
            }else{
                if(gala.contientReservationEnAttente(reservation)){
                    while (true){
                        ihm.message("Vous avez demander "+reservation.getNbDePlace()+" place(s)");
                        if(ihm.confirmationReservation()){
                            if(ihm.demandePlanDesTables()) {
                                ihm.message(gala.toStringLesTables(utilisateur));
                                int numTable = ihm.choixTable(utilisateur);
                                Table table = gala.retrouverTable(numTable);
                                ihm.message("vous avez choisit la table numero "+numTable+" : \n"+table); // toString de table qui montre la composition de la table choisit
                                int nombreDePlace = ihm.nbrPlace(numTable);
                                if (table.getNbPlaceLibre() <= nombreDePlace) {
                                    ctlReservation(numTable, nombreDePlace);
                                    break;
                                }
                            }else{
                                int nombreDePlace = ihm.nbrPlace(reservation.getNbDePlace());
                                ctlReservation(nombreDePlace);
                                break;
                            }

                        }else{
                            ihm.message("Vous n'avez pas confirmez votre reservation");
                            break;
                        }
                    }
                }
                ihm.message("Vous avez reservez déjà "+reservation.getNbDePlace()+" place(s)");
            }
        }
    }



    /**
     * Créer une nouvelle reservation attribué automatiquement avec une table avec assez de place libre
     * attention a distingué etudiant et personnel pour savoir où est créer la reservation et
     * où chercher une table avec assez de places libre
     * @param nombreDePlace
     * @throws Quitter renvoie une exeption si une erreur dans la création de la réservation est arrivé et que l'utilisateur quitte
     */
    private void ctlReservation(int nombreDePlace) throws Quitter {
        try {
            gala.reservationAutomatique(utilisateur,nombreDePlace);
        } catch (Exception e) {
            ihm.message(e.getMessage());
            ctlMenuGestionPlace();
        }
    }

    /**
     * Créer une nouvelle reservation dans gala attention distingué etudiant et personnel
     * pour savoir où créer la reservation
     * @param numTable numero de table pour créer la reservation
     * @param nombreDePlace nombre de table reservée
     */
    private void ctlReservation(int numTable, int nombreDePlace) {
        gala.ajouterReservation(numTable,nombreDePlace,utilisateur);
    }


    /**
     * Mais à jour le set des reservation dont la réservation est en attente
     * en fonction du nombre total de place et du nombre déjà en attente.
     *
     * Cette méthode est executer à chaque lancement quand le gala est
     * dans moins d'un mois (géréer dans le controleur)
     */
    public void ctlMiseAJourReservationAttente() {
        gala.miseAJourReservationAttente();
    }

}
