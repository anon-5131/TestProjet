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

    public int getNumeroPersonnel() {
        return numeroPersonnel;
    }
}
