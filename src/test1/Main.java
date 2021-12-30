package test1;

import test1.model.Quitter;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate dateDuGala = null;
        try{
            dateDuGala = LocalDate.of(2021, 12,31 );
            Controleur controleur = new Controleur(dateDuGala);
            if (!(controleur.ctlUtilisateurInscrit())) {
                controleur.ctlInscription();
            }
            while (true){ // attend l'utilisation de ctlQuitter (qui throw l'extption Quitter) pour quitter
                controleur.ctlMenuGestionPlace();
            }
        } catch (Quitter e) {
            System.out.println(e.getMessage());
        }
    }

}




