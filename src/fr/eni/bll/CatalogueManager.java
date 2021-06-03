package fr.eni.bll;

import fr.eni.bllException.BLLException;
import fr.eni.dal.DALException;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.jdbc.ArticleDAO;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.util.List;


public class CatalogueManager {
    //ATTRIBUT D'INSTANCE
    private ArticleDAO articleDao = DAOFactory.getArticleDAO();

    //pour faire un singleton, il faut instance static et privé, un constructeur vide prive et une methode getinstance public et privé
    private static CatalogueManager instance;

    public CatalogueManager() {
    }

    public static CatalogueManager getInstance() {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }

    // Méthode pour afficher une liste
    public List<Article> getCatalogue() throws BLLException {
        try {
            return articleDao.selectAll();
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }


    // Méthode ajouter un article au catalogue
    public void addArticle(Article a) throws BLLException {
        this.validerArticle(a);
        this.articleDao.insert(a);
    }


    // Méthode modifier un article au catalogue
    public void updateArticle(Article a) throws BLLException {
        validerArticle(a);
        this.articleDao.update(a);
    }


    // Méthode remplacer un article
    public void removeArticle(int index) throws BLLException {
        try {
            this.articleDao.delete(index);
        } catch (DALException e) {
            throw new BLLException("Erreur ds la BLL");
        }

    }

    // Méthode getArticle un article
    public Article getArticle(int index) throws BLLException {
        return this.articleDao.selectById(index);

    }

    // Méthode valider
    private void validerArticle(Article a) throws BLLException {
        if (a == null) {
            throw new BLLException("L'article est null");
        }
        if (a instanceof Ramette ){
            if(((Ramette) a).getGrammage() <= 0) {
                throw new BLLException("Son grammage n'est pas valide");
            }
        }
        else if (a instanceof Stylo){
            if(((Stylo) a).getCouleur() == null || ((Stylo) a).getCouleur().trim().length() == 0) {
                throw new BLLException("La couleur n'est pas valide");
                    }
        }
        /*if (a.getReference() == null || a.getReference().trim().length() == 0) {
            throw new BLLException("La référence est obligatoire");
        }*/

    }

}
