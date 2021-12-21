package test1;

public class Etudiant extends Participant {
    private int anneeDeFormation;
    private int numeroEtudiant;



    public Etudiant(int numeroEtudiant,String nom, String prenom, String numeroTel, String mail, int anneeDeFormation){
        super(prenom, nom, numeroTel, mail);
        this.numeroEtudiant = numeroEtudiant;
        this.anneeDeFormation = anneeDeFormation;
    }

    // utiliser pour faire des comparaison grâce à équals pour faire des contains dans des set par exemple
    public Etudiant(int numeroEtudiant, String nom){
        this.nom=nom;
        this.numeroEtudiant=numeroEtudiant;
    }

    public boolean equals(Etudiant etudiant) {
        return numeroEtudiant==etudiant.numeroEtudiant;
    }

    /**
     * lors de l'identification pour savoir si le numero correspond en même temps que le nom
     * @param etudiant element à comparer
     * @return true si le numero et le nom correspondes
     */
    public boolean correspond(Etudiant etudiant){
        return numeroEtudiant==etudiant.numeroEtudiant && nom.equals(etudiant.nom);
    }

    public int getAnneeDeFormation() {
        return anneeDeFormation;
    }
}
