package test1;

import java.io.Serializable;

abstract class Participant implements Serializable {
    protected String nom;
    protected String prenom;
    protected String numeroTel;
    protected String mail;

    public Participant(String prenom, String nom, String numeroTel, String mail){
        this.prenom = prenom;
        this.nom = nom;
        this.numeroTel = numeroTel;
        this.mail = mail;
    }


    public String getNom() {
        return nom;
    }

    abstract public int getNumero();
}
