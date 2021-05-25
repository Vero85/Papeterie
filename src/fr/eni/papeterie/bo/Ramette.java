package fr.eni.papeterie.bo;

public class Ramette extends Article {
    private int grammage;

    public Ramette(int idArticle, String reference, String marque, String designation, float prixUnitaire, int qteStock) {
        super(idArticle, reference, marque, designation, prixUnitaire, qteStock);
    }

    public Ramette(String reference, String marque, String designation, float prixUnitaire, int qteStock, int grammage) {
        super(reference, marque, designation, prixUnitaire, qteStock);
        this.grammage = grammage;
    }

    public Ramette(int i, String clairef, String cra4S, String ramette_a4_sup, float v, int i1, int i2) {
    }

    /*
    Getter
    */
    public int getGrammage() {
        return grammage;
    }

    /*
    Setter
    */
    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    /*
    Méthode toString
    */
    @Override
    public String toString() {
        return "Ramette{" +
                "grammage=" + grammage +
                '}';
    }

    /*
    Méthode Ramette
    */
    public Ramette() {

    }
}
