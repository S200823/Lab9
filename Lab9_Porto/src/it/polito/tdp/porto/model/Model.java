package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.DAO.DAO;

public class Model {

	DAO dao = new DAO();

	List<Creator> creators = new ArrayList<Creator>();
	List<Article> articles = new ArrayList<Article>();

	UndirectedGraph<Creator, DefaultEdge> grafo;

	public List<Creator> getAllCreators() {
		return creators;
	}

	public List<Article> getAllArticles() {
		return articles;
	}

	public void creaGrafo() {
		creators = dao.getAllCreators();
		articles = dao.getAllArticles();
		grafo = new SimpleGraph<>(DefaultEdge.class);

		// Aggiungo articoli ad autori
		for (Creator c : creators) {
			List<Article> articlesPerCreator = dao.getArticlesPerCreator(c, articles);
			c.setArticles(articlesPerCreator);
		}
		// Aggiungo gli autori agli articoli
		for (Article a : articles) {
			List<Creator> creatorsPerArticle = dao.getCreatorsPerArticles(a, creators);
			a.setCreators(creatorsPerArticle);
		}
		// Aggiungo i vertici
		Graphs.addAllVertices(grafo, creators);
		// Aggiungo gli archi
		for (Creator c1 : creators) {
			for (Creator c2 : creators) {
				for (Article a : c1.getArticles()) {
					if (a.getCreators().contains(c2)) {
						if (!c1.equals(c2)) // Solo se sono diversi
							grafo.addEdge(c1, c2);
					}
				}
			}
		}
	}

	public List<Creator> getCoautori(Creator autore) {
		return Graphs.neighborListOf(grafo, autore);
	}

	public List<Article> getCollaborazioni(Creator c1, Creator c2) {
		List<Article> articles1 = c1.getArticles();
		List<Article> articles2 = c2.getArticles();
		List<Article> collaborazioni = new ArrayList<Article>();
		for(Article a1 : articles1){
			for(Article a2 : articles2){
				if(a1.equals(a2))
					collaborazioni.add(a2);
			}
		}
		return collaborazioni;
	}
}
