package test1;

public class Etudiant extends Participant {
    private int anneeDeFormation;
    private int numeroEtudiant;



    public Etudiant(int numeroEtudiant,String nom, String prenom, String numeroTel, String mail, int anneeDeFormation){
        super(prenom, nom, numeroTel, mail);
        this.numeroEtudiant = numeroEtudiant;
        this.anneeDeFormation = anneeDeFormation;
    }

    public boolean equals(Etudiant etudiant) {
        return numeroEtudiant==etudiant.numeroEtudiant;
    }

    public int getAnneeDeFormation() {
        return anneeDeFormation;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }
}
