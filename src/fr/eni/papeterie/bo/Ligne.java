package fr.eni.papeterie.bo;

public class Ligne {
    protected int qte;
    //association unidirectionnelle (fleche)
    protected Article article;

    /*
        Constructeur
     */

    public Ligne(int qte, Article article) {
        this.qte = qte;
        this.article = article;
    }

    /*
       Getter
     */
    public int getQte() {
        return qte;
    }

    public Article getArticle() {
        return article;
    }

    /*
        Setter
     */
    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    /*
        Méthode prenant 1 article et allant chercher le prix unitaire.
     */
    public float getPrix() {
        return this.article.getPrixUnitaire();
    }

    /*
        Méthode toString
     */
    @Override
    public String toString() {
        return "Ligne{" +
                "qte=" + qte +
                ", article=" + article +
                '}';
    }
}
