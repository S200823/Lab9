package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

public class Creator implements Comparable<Creator> {

	private int idCreator;
	private String familyName;
	private String givenName;

	private List<Article> articles;

	public Creator(int idCreator, String cognome, String nome) {
		this.idCreator = idCreator;
		this.familyName = cognome;
		this.givenName = nome;
		articles = new ArrayList<Article>();
	}

	public Creator(int idCreator) {
		this.idCreator = idCreator;
	}

	public int getIdCreator() {
		return idCreator;
	}

	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCreator;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creator other = (Creator) obj;
		if (idCreator != other.idCreator)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return givenName + " " + familyName;
	}

	@Override
	public int compareTo(Creator arg0) {
		if (this.familyName.compareTo(arg0.familyName) == 0)
			return this.givenName.compareTo(arg0.givenName);
		return this.familyName.compareTo(arg0.familyName);
	}
}
