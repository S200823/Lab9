package it.polito.tdp.porto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;

public class DAO {

	public List<Creator> getAllCreators() {
		final String sql = "SELECT * FROM creator";
		List<Creator> creators = new ArrayList<Creator>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int idCreator = res.getInt("id_creator");
				String familyName = res.getString("family_name");
				String givenName = res.getString("given_name");
				creators.add(new Creator(idCreator, familyName, givenName));
			}
			res.close();
			st.close();
			conn.close();
			return creators;
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Article> getAllArticles() {
		final String sql = "SELECT * FROM article";
		List<Article> articles = new ArrayList<Article>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				long ePrintId = res.getLong("eprintid");
				int year = res.getInt("year");
				String title = res.getString("title");
				articles.add(new Article(ePrintId, year, title));
			}
			res.close();
			st.close();
			conn.close();
			return articles;
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Article> getArticlesPerCreator(Creator c, List<Article> articles) {
		final String sql = "SELECT article.eprintid FROM article, authorship WHERE article.eprintid = authorship.eprintid and authorship.id_creator = ?";
		List<Article> articlesPerCreator = new ArrayList<Article>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, c.getIdCreator());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				long ePrintId = res.getLong("eprintid");
				Article article = articles.get(articles.indexOf(new Article(ePrintId)));
				articlesPerCreator.add(article);
			}
			res.close();
			st.close();
			conn.close();
			return articlesPerCreator;
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Creator> getCreatorsPerArticles(Article a, List<Creator> creators) {
		final String sql = "SELECT creator.id_creator FROM creator, authorship WHERE creator.id_creator = authorship.id_creator and authorship.eprintid = ?";
		List<Creator> creatorsPerArticles = new ArrayList<Creator>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, a.getePrintId());
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int idCreator = res.getInt("id_creator");
				Creator creator = creators.get(creators.indexOf(new Creator(idCreator)));
				creatorsPerArticles.add(creator);
			}
			res.close();
			st.close();
			conn.close();
			return creatorsPerArticles;
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Article> getCollaborazioni(Creator c1, Creator c2) {
		// TODO Auto-generated method stub
		return null;
	}
}
