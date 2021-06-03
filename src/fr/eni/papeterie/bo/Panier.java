package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    // Atribut d'instance
    private final List<Ligne> lignesPanier;
    private float montant;


    /*
        Constructeur vide
     */

    public Panier() {
        this.lignesPanier = new ArrayList<>();
    }

    /*
        Getter
     */
   public float getMontant() {
        return montant;
    }

    public List <Ligne> getLignesPanier() {
        return lignesPanier;
    }

    /*
        Méthode ajouter 1 ligne
     */
    public void addLigne(Article article, int qte) {
        Ligne ligneAdding = new Ligne(qte, article);
        lignesPanier.add (ligneAdding);
    }

    /*
        Retourne la ligne sélectionnée du panier ou null si pas trouvée
     */

    public final Ligne getLigne(int index){
        return lignesPanier.get(index);
    }

    /*
        Présente le détail du panier :
     */

    @Override
    public String toString() {
        return "Panier{" +
                "lignesPanier=" + lignesPanier +
                ", montant=" + montant +
                ", lignesPanier=" + lignesPanier +
                '}';
    }

    /*
        Supprimer la ligne du panier. La quantité en stock augmente de la quantité associé à la ligne
     */
    public void removeLigne(int index){
        lignesPanier.remove(index);
    }


    /*
        Modifier la quantité placée ds le panier. (la quantité en stock augmente ou diminue en fonction de cette nouvelle qté)
     */
    public void updateLigne(int index, int newQte) {
        this.getLigne(index).setQte(newQte);
    }


    public void setMontant(float montant) {
        this.montant = montant;
    }
}
