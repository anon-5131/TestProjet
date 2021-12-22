package test1;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable, Comparable<Reservation> {
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


    /**
     * Calcul le montant de la reservation en fonction des tarif pour un étudiant
     * ou un membre du personnel
     * @param possesseur "personnel" ou "etudiant"
     * @param nbDePlace nombre de place reserver pour savoir le nombre d'acompagnateur
     * @return le prix de la reservation
     */
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

    public Participant getPossesseur() {
        return possesseur;
    }

    @Override
    public int compareTo(Reservation o) {
        if (possesseur instanceof Personnel){
            return 0;
        }else {
            if (((Etudiant)possesseur).getAnneeDeFormation()==5 && ((Etudiant)o.getPossesseur()).getAnneeDeFormation()==5){
                return 0;
            }else if(((Etudiant)possesseur).getAnneeDeFormation()==5 && ((Etudiant)o.getPossesseur()).getAnneeDeFormation()!=5){
                return 1;
            }else if(((Etudiant)possesseur).getAnneeDeFormation()!=5 && ((Etudiant)o.getPossesseur()).getAnneeDeFormation()==5){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
