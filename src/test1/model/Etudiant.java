package test1.model;


import java.util.Objects;

public class Etudiant extends Participant {
    private int anneeDeFormation;
    private int numeroEtudiant;



    public Etudiant(int numeroEtudiant,String nom, String prenom, String numeroTel, String mail, int anneeDeFormation){
        super(prenom, nom, numeroTel, mail);
        this.numeroEtudiant = numeroEtudiant;
        this.anneeDeFormation = anneeDeFormation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return numeroEtudiant == etudiant.numeroEtudiant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroEtudiant);
    }

    @Override
    public int getNumero() {
        return numeroEtudiant;
    }

    public int getAnneeDeFormation() {
        return anneeDeFormation;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    @Override
    public String toString() {
        return super.toString() +
                "anneeDeFormation=" + anneeDeFormation +
                ", numeroEtudiant=" + numeroEtudiant;
    }
}
