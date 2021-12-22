package test1;

public class Personnel extends Participant {
    private int numeroPersonnel;

    public Personnel(int numeroPersonnel,String nom, String prenom, String numeroTel, String mail){
        super(prenom, nom, numeroTel, mail);
        this.numeroPersonnel = numeroPersonnel;


    }

    public boolean equals(Personnel personnel) {
        return numeroPersonnel==personnel.numeroPersonnel;
    }

    public int getNumeroPersonnel() {
        return numeroPersonnel;
    }
}
