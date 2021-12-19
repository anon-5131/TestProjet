package test1;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private LocalDate date;
    private int numero;
    private int nbDePlace;
    private double montant;
    private Participant possesseur;

    public int getNumero() {
        return numero;
    }

    public int getNbDePlace() {
        return nbDePlace;
    }
}
