package test1;

public class Personnel extends Participant {
    private int numeroPersonnel;

    public Personnel(int numeroPersonnel,String nom, String prenom, String numeroTel, String mail){
        super(prenom, nom, numeroTel, mail);
        this.numeroPersonnel = numeroPersonnel;


    }

    // utiliser pour faire des comparaison grâce à équals pour faire des contains dans des set par exemple
    public Personnel(int numeroPersonnel, String nom){
        this.numeroPersonnel=numeroPersonnel;
        this.nom=nom;
    }

    public boolean equals(Personnel personnel) {
        return numeroPersonnel==personnel.numeroPersonnel;
    }

    /**
     * * lors de l'identification pour savoir si le numero correspond en même temps que le nom
     * @param personnel element à comparer
     * @return true si le numero et le nom correspondes
     */

    public boolean correspond(Personnel personnel){
        return numeroPersonnel==personnel.numeroPersonnel && nom.equals(personnel.nom) ;
    }
}
