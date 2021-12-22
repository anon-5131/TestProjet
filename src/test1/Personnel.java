package test1;


import java.util.Objects;

public class Personnel extends Participant {
    private int numeroPersonnel;

    public Personnel(int numeroPersonnel,String nom, String prenom, String numeroTel, String mail){
        super(prenom, nom, numeroTel, mail);
        this.numeroPersonnel = numeroPersonnel;


    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Personnel){
            return numeroPersonnel == ((Personnel)obj).numeroPersonnel;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroPersonnel);
    }

    public int getNumeroPersonnel() {
        return numeroPersonnel;
    }
}
