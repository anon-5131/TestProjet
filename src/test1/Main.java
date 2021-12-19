package test1;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate dateDuGala = LocalDate.of(2021,12,25);
        Controleur controleur = new Controleur(dateDuGala);
        if(!(controleur.ctlUtilisateurInscrit())){
            controleur.ctlInscription();
        }
        controleur.ctlMenuGestionPlace();

    }

}




