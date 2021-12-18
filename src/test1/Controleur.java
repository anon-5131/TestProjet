package test1;

import java.time.LocalDate;

public class Controleur {
    private Ihm ihm;
    private Gala gala;
    private String identifiantUtilisateur;
    private String typeParticipant; // sera utiliser pour choisir quel méthode utiliser avec if (typeParticipant = "etudiant")...


    public Controleur(LocalDate dateDuGala){
        // TODO mettre en place le systeme de sauvegarde et de chargement avec un try catch / scanner(new File ())

        ihm = new Ihm();
        gala = new Gala();
        ctlIdentification(); // pour remplir les attributs identifiantUtilisateur et typeParticipant, et obligé l'utilisateur à s'identifier
        if (LocalDate.now().isAfter(dateDuGala.minusMonths(1))){
            ctlMiseAJourReservationAttente();
        }
    }


    /**
     * Utilise la méthode identification de l'ihm pour récuperer
     * l'identifiant de l'utilisateur qui veux s'inscire et rempli
     * les attributs identifiantUtilisateur et typeParticipant
     *
     * Utilisation d'un try cath pour redemander si l'identification est fausse
     */
    public void ctlIdentification (){

    }


    /**
     * Permet de savoir si un utilisateur est déjà inscrit grâce au set de la classe
     * Gala (lePersonnelInscrit et lesEtudiantsInscrit
     * Attention à séparer deux méthodes ou parties entre les etudiant et le personnel
     * @return vrai si le participant est inscrit sinon false
     */
    public boolean ctlUtilisateurInscrit(){

    }

    /**
     * Inscrit un participant dans le set de la classe Gala correspondante (on a
     * deja tester si il était présent grace à ctlUtilisateurInscrit)
     */
    public void ctlInscription(){

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

    public void ctlMenuGestionPlace(){

    }


    /**
     * Supprime toutes les demandes de reservation et ou les reservation
     * qui le concerne  puis,
     * Désiscrit un participant dans le set de la classe Gala correspondante
     */
    public void ctlDesinscription(){

    }




    /**
     * quitte l'application (je sais pas comment faire)
     *
     */
    public void ctlQuitter(){

    }

    /**
     * Identifie si le participant est un Etudiant ou un membre du Personnel
     *
     * Si Personnel :
     *
     * Si l'utilisateur à déjà une reservation, affiche le nombre de place et
     * le numero de table
     *
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
    public void ctlgestionPlace(){

    }


    /**
     * Mais à jour le set des reservation dont la réservation est en attente
     * en fonction du nombre total de place et des désinscriptions.
     *
     * Cette méthode est executer à chaque lancement quand le gala est
     * dans moins d'un mois (géréer dans le controleur)
     */
    public void ctlMiseAJourReservationAttente(){

    }

}
