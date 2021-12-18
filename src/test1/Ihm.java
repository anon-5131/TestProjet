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


    public String identificationNom() {
        System.out.println("Entrer votre nom");
        return input.next();
    }

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

    public void message(String msg){
        System.out.println(msg);
    }

}
