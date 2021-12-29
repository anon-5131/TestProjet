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

        //Ihm ihm = new Ihm();

        //System.out.println(ihm.demandePlanDesTables());
        //System.out.println(ihm.choixTable("personnel"));
        //System.out.println(ihm.choixTable("etudiant"));
        //System.out.println(ihm.nbrPlace(10));

            /*
        try {
            Gala gala = new Gala();
            //System.out.println("bonsoir");
            //System.out.println(gala.nbrPlaceMax(new Etudiant(1234,"jojo","jo","06","email",4)));
            //System.out.println(gala.retrouverReservation("etudiant", 1111));
            System.out.println(gala.checkInscrit("etudiant","MARTIN",2165001));
            gala.desinscription("etudiant",2165001);
            System.out.println(gala.checkInscrit("etudiant","MARTIN",2165001));
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        */

    }

}




