package fr.eni.dal.jdbc;
import fr.eni.dal.DALException;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticleDAOJdbcImpl implements ArticleDAO {
    private final String URL = Settings.getPropriete("url");
    private final String SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle=?";
    private final String SQL_DELETE = "DELETE FROM Articles WHERE idArticle=?";
    private final String SQL_UPDATE = "UPDATE Articles set " +
            "reference=?, marque=?, prixUnitaire=?, designation=?, qteStock=?, grammage=?, couleur=? +" +
            "WHERE idArticle=?";
    private final String SQL_INSERT = "INSERT INTO Articles " +
            "(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) " +
            "VALUES(?,?,?,?,?,?,?,?)";
    private final String SELECT_ALL = "SELECT * FROM Articles;";

    @Override
    public void update(Article article) {
        try {
            Connection connection = JdbcTools.recupConnection();
            PreparedStatement etat = connection.prepareStatement(this.SQL_UPDATE);
            etat.setString(1, article.getReference());
            etat.setString(2, article.getMarque());
            etat.setString(3, article.getDesignation());
            etat.setFloat(4,  article.getPrixUnitaire());
            etat.setInt(5, article.getQteStock());
            etat.setInt(8, article.getIdArticle());

            if (article instanceof Stylo) {
                etat.setString(7, ((Stylo) article).getCouleur());
            } else if (article instanceof Ramette) {
                etat.setInt(6, ((Ramette) article).getGrammage());
            }
            etat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insert(Article article) {
        try (
                Connection connection = JdbcTools.recupConnection();
                PreparedStatement etat = connection.prepareStatement(this.SQL_INSERT);
        ) {
            etat.setString(1, article.getReference());
            etat.setString(2, article.getMarque());
            etat.setString(3, article.getDesignation());
            etat.setFloat(4,  article.getPrixUnitaire());
            etat.setInt(5, article.getQteStock());
            if (article instanceof Ramette) {
                etat.setInt(6, ((Ramette) article).getGrammage());
                etat.setString(8, "ramette");
            }
            if (article instanceof Stylo) {
                etat.setString(7, ((Stylo) article).getCouleur());
                etat.setString(8, "STYLO");
            }
            etat.executeUpdate(); // Je récupère l'ID auto généré par la méthode insert
            ResultSet rs = etat.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                article.setIdArticle(id); // et je le met ds l'article en utilisant le setter
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Article selectById(int id) {
        Article article = null;
        try (
                Connection connection = JdbcTools.recupConnection();
                Statement etat = connection.createStatement()
        ) {
            String sql = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur," +
                    "type FROM Articles WHERE idArticle = " + id + ";";
            ResultSet rs = etat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("type").trim().equalsIgnoreCase("RAMETTE")) {
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
                if (rs.getString("type").trim().equalsIgnoreCase("STYLO")) {
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(article);
        return article;
    }
    @Override
    public void delete(int id) throws DALException {
        try (Connection connection = JdbcTools.recupConnection()){
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_DELETE);
            reqPreparee.setInt(1, id);
            reqPreparee.execute();
            } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public List<Article> selectAll() throws DALException {
        List<Article> articles = new ArrayList<>();
        try (
                Connection connection = JdbcTools.recupConnection();
                PreparedStatement reqPreparee = connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet rs = reqPreparee.executeQuery();
            while (rs.next()) {
                Article article = null;
                if (rs.getString("type").equalsIgnoreCase("ramette")) {
                    article = new Ramette(rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage"));
                }
                if (rs.getString("type").equalsIgnoreCase("stylo")) {
                    article = new Stylo(rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur"));
                }
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
        return articles;
    }

}
