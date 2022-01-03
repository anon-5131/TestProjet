package test1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Table implements Serializable {
    private int numero;
    private int nbPlaceLibre;
    private Set<Reservation> reservationsAssociee;
    private static int numeroSuivant=0;

    public Table (){
        numero = numeroSuivant++;
        nbPlaceLibre=Gala.NB_PLACES_TABLE;
        reservationsAssociee = new HashSet<>();

    }


    public int getNbPlaceLibre() {
        return nbPlaceLibre;
    }

    public int getNumero() {
        return numero;
    }

    /**
     * retourne la composition (les noms) de la tables et ajoute si
     * la personne est accompagné où non et indique le nombre
     * de place libre (en contant le nombre de ligne)
     * @return composition de la table
     */
    @Override
    public String toString() {
        String valeurDeRetour="Composition de la table numero"+numero+" :\n";
        for (Reservation reservation : reservationsAssociee){
            valeurDeRetour+="- "+reservation.getPossesseur().getNom();
            if (reservation.getNbDePlace() > 1){
                valeurDeRetour+=" + accompagnant\n";
            }else{
                valeurDeRetour+="\n";
            }
        }
        for (int i=0;i<nbPlaceLibre;i++){
            valeurDeRetour+="- Place disponible\n";
        }
        return valeurDeRetour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return numero == table.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }


    /**
     * ajoute une nouvelle resservation au set de la liste et change
     * le nombre de place libre en consequence
     * @param reservation la reservation que l'on veut ajouter
     */
    public void ajouterReservation(Reservation reservation) {
        reservationsAssociee.add(reservation);
        nbPlaceLibre-=reservation.getNbDePlace();
    }

    /**
     * supprimer la reservation passé en paramètre et change le nombre
     * de place libre en consequence
     * @param reservation que l'on veut supprimer de la table
     */
    public void supprimerReservation(Reservation reservation) {
        reservationsAssociee.remove(reservation);
        nbPlaceLibre+=reservation.getNbDePlace();
    }


}
