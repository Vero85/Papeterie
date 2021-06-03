package fr.eni.dal;

import fr.eni.dal.jdbc.ArticleDAO;
import fr.eni.dal.jdbc.ArticleDAOJdbcImpl;

public class DAOFactory {
   public static ArticleDAO getArticleDAO() {
       ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
       return articleDAO;}
}

