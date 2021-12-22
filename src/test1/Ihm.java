package test1;

import java.util.Scanner;

public class Ihm {
    Scanner input;

    public Ihm() {
        input = new Scanner(System.in);
    }


    /**
     * Demande à l'utilisateur si il est un etudiant ou un membre du personnel
     *
     * @return le type choisit
     */
    public String identificationType() {
        while (true) {
            System.out.println("Choissiser quel type d'utilisateur vous êtes :\n1 - membre du Personnel\n2 - Etudiant");
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    int reponse = input.nextInt();
                    if (reponse == 1) {
                        return "personnel";
                    } else if (reponse == 2) {
                        return "etudiant";
                    }
                }else{
                    input.next(); // cas ou l'input n'est pas un int
                }
                System.out.println("Valeur incorrect, veuillez saisir une valeur correct");
            }
        }
    }

    /**
     * Demande à l'utilisateur son nom et le retourne
     * @return nom demander à l'utilisateur
     */
    public String identificationNom() {
        System.out.println("Entrer votre nom");
        return input.next();
    }

    /**
     * Demande à l'utilisateur son numéro et le retourne (int uniquement)
     * @return numero demander à l'utilisateur
     */
    public int identificationNum() {
        while(true) {
            System.out.println("Entrer votre numero");
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    return input.nextInt();
                }
                System.out.println("Valeur incorrect, veuillez saisir une valeur correct");
                input.next();
            }
        }
    }

    /**
     * envoie à l'utilisateur le message en parametre
     * @param msg le message que l'on veut envoyer
     */
    public void message(String msg){
        System.out.println(msg);
    }

    /**
     * demande à l'utilisateur si il veux voir le plan des tables
     * @return true si oui/Oui/O/Yes/yes/y || false si non/Non/N/No/no
     */
    //public boolean demandePlanDesTables() {
    //}

    /**
     * Demande à l'utilisateur le numero de table choisit
     * @param typeParticipant si etudiant personne choisit entre 1 et 10 si personnel entre 11 et 25
     * @return le nombre choisit
     */
    //public int choixTable(String typeParticipant) {
    //}

    /**
     * Demande le nombre de place que l'utilisateur choisit, renvoie se nombre si il est compris entre 1 et limiteNombreDePlace
     * @param limiteNombreDePlace la limite que l'utilisateur peut choisir
     * @return le nombre de place choisit
     */
    //public int nbrPlace(int limiteNombreDePlace) {
    //}


    /**
     * Demmande à l'utilisateur si il veut confirmer ça reservation
     * @return true si oui/Oui/O/Yes/yes/y || false si non/Non/N/No/no
     */
    //public boolean confirmationReservation() {
    //}
}
