package fr.eni.papeterie.bo;

public class Stylo extends Article {
    private String couleur;

    /*
        Constructeur
     */
    public Stylo(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    public Stylo(String reference, String marque, String designation, float prixUnitaire, int qteStock, String couleur) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    /*
    constructeur vide
     */
    public Stylo() {

    }

    /*
     Getter
   */
    public String getCouleur() {
        return couleur;
    }

    /*
      Setter
    */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /*
    Méthode toString
    */
    @Override
    public String toString() {
        return "Stylo{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
