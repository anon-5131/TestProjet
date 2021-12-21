package test1;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private LocalDate date;
    private int numeroTable;
    private int nbDePlace;
    private double montant;
    private Participant possesseur;

    public Reservation(int numeroTable,int nbDePlace,Participant possesseur){
        date=LocalDate.now();
        this.numeroTable=numeroTable;
        this.nbDePlace=nbDePlace;
        montant=calculMontant(possesseur,nbDePlace);
        this.possesseur=possesseur;
    }

    private double calculMontant(Participant possesseur, int nbDePlace) {
        double montant = 0;
        if (possesseur instanceof Personnel){
            montant=Gala.TARIF3;
        }else{
            if ( ((Etudiant)possesseur).getAnneeDeFormation()==5 ){
                montant=Gala.TARIF1;
            }else{
                montant=Gala.TARIF2;
            }
        }
        montant+=Gala.TARIF3*(nbDePlace-1); // nbDePlace-1 == nb accompagnateur
        return montant;
    }


    public int getNumeroTable() {
        return numeroTable;
    }

    public int getNbDePlace() {
        return nbDePlace;
    }
}
