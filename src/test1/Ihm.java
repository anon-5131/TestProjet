package test1;

import test1.model.Etudiant;
import test1.model.Participant;
import test1.model.Personnel;

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
        String valeurDeRetour = "";
        while ("".equals(valeurDeRetour)) {
            System.out.println("Choissiser quel type d'utilisateur vous êtes :\n1 - membre du Personnel\n2 - Etudiant");
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    int reponse = input.nextInt();
                    if (reponse == 1) {
                        valeurDeRetour = "personnel";
                    } else if (reponse == 2) {
                        valeurDeRetour = "etudiant";
                    }else{
                        System.out.println("Valeur incorrect, veuillez saisir une valeur correct");
                    }
                }else{
                    input.next(); // cas ou l'input n'est pas un int
                    System.out.println("Valeur incorrect, veuillez saisir une valeur correct");
                }

            }
        }
        return valeurDeRetour;
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
    public boolean demandePlanDesTables() {
        while (true) {
            System.out.println("Voulez vous voir le plan des tables ?(oui/non)");
            if (input.hasNext()) {
                String reponse = input.next().toLowerCase();
                if (reponse.equals("oui") || reponse.equals("o") || reponse.equals("yes") || reponse.equals("y")) {
                    return true;
                } else if (reponse.equals("non") || reponse.equals("n") || reponse.equals("no")) {
                    return false;
                }
            }else{
                System.out.println("Erreur, veuillez choisir 'oui' ou 'non'");
                input.next();
            }
        }
    }

    /**
     * Demande à l'utilisateur le numero de table choisit
     * @param utilisateur si type est un etudiant personne choisit
     *                   entre 1 et 10 si personnel entre 11 et 25
     * @return le nombre choisit
     */
    public int choixTable(Participant utilisateur) {
        while (true) {
            System.out.println("Veuillez entrer le numéro de la table");
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    int reponse = input.nextInt();
                    if (utilisateur instanceof Etudiant && reponse >0 && reponse<=10 || utilisateur instanceof Personnel && reponse > 10 && reponse<=25) {
                        return reponse;
                    } else {
                        System.out.println("Erreur, veuillez choisir le bon numéro en fonction de votre type de participant");
                    }
                } else {
                    System.out.println("Erreur, veuillez entrer un entier");
                    input.next();
                }
            }
        }
    }

    /**
     * Demande le nombre de place que l'utilisateur choisit, renvoie se nombre si il est compris entre 1 et limiteNombreDePlace
     * @param limiteNombreDePlace la limite que l'utilisateur peut choisir
     * @return le nombre de place choisit
     */
    public int nbrPlace(int limiteNombreDePlace) {
        while(true){
            System.out.println("Limite du nombre de place " + limiteNombreDePlace);
            System.out.println("Choisir le nombre de place");
            if (input.hasNext()){
                if (input.hasNextInt()){
                    int reponse = input.nextInt();
                    if (reponse> 0 && reponse<=limiteNombreDePlace){
                        return reponse;
                    }else{
                        System.out.println("Erreur, choisir un nombre entre 1 et " + limiteNombreDePlace);

                    }

                }else{
                    System.out.println("Veuillez entrer un entier");
                    input.next();
                }
            }
        }
    }


    /**
     * Demmande à l'utilisateur si il veut confirmer ça reservation
     * @return true si oui/Oui/O/Yes/yes/y || false si non/Non/N/No/no
     */
    public boolean confirmationReservation() {
        while (true) {
            System.out.println("Voulez vous confirmer cette reservation ?(oui/non)");
            if (input.hasNext()) {
                String reponse = input.next().toLowerCase();
                if (reponse.equals("oui") || reponse.equals("o") || reponse.equals("yes") || reponse.equals("y")) {
                    return true;
                } else if (reponse.equals("non") || reponse.equals("n") || reponse.equals("no")) {
                    return false;
                }
            }else{
                System.out.println("Erreur, veuillez choisir 'oui' ou 'non'");
                input.next();
            }
        }
    }

    public int menuGestionPlace() {
        while (true) {
            System.out.println("Veuiller choisir ce que vous voulez faire entre :\n" +
                    "1 – Gérer les places du dîner\n" +
                    "2 – Se désinscrire\n" +
                    "3 – Quitter");
            if (input.hasNext()) {
                if (input.hasNextInt()) {
                    int reponse = input.nextInt();
                    if (reponse >= 1 && reponse <= 3) {
                        return reponse;
                    } else {
                        System.out.println("Erreur, veuillez entrer un chiffre entre 1 et 3");
                    }
                } else {
                    System.out.println("Erreur, veuillez entrer un chiffre entre 1 et 3");
                    input.next();
                }
            }
        }
    }
}
