package test1;

import java.io.Serializable;

abstract public class Participant implements Serializable {
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

    /**
     * renvoi le numeroEtudiant ou le numeroPersonnel en fonction
     * dans quel sous-classe on est
     * @return le numero qui definit l'utilisateur (numeroPersonnel ou numeroEtudiant)
     */
    abstract public int getNumero();

    abstract public boolean equals(Object o);

    @Override
    public String toString() {
        return "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numeroTel='" + numeroTel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}
