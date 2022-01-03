package test1;


import java.util.Objects;

public class Personnel extends Participant {
    private int numeroPersonnel;

    public Personnel(int numeroPersonnel,String nom, String prenom, String numeroTel, String mail){
        super(prenom, nom, numeroTel, mail);
        this.numeroPersonnel = numeroPersonnel;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnel personnel = (Personnel) o;
        return numeroPersonnel == personnel.numeroPersonnel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPersonnel);
    }

    @Override
    public int getNumero() {
        return numeroPersonnel;
    }

    @Override
    public String toString() {
        return super.toString() + ", numeroPersonnel='" + numeroPersonnel+'\'';
    }

    public int getNumeroPersonnel() {
        return numeroPersonnel;
    }

}
