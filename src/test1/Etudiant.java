package test1;


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
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Etudiant){
            return numeroEtudiant == ((Etudiant)obj).numeroEtudiant;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroEtudiant);
    }

    public int getAnneeDeFormation() {
        return anneeDeFormation;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

}
