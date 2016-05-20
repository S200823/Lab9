package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

public class Article implements Comparable<Article> {

	private long ePrintId;
	private int year;
	private String title;

	private List<Creator> creators;

	public Article(long ePrintId, int year, String title) {
		this.ePrintId = ePrintId;
		this.year = year;
		this.title = title;
		creators = new ArrayList<Creator>();
	}

	public Article(long ePrintId) {
		this.ePrintId = ePrintId;
	}

	public long getePrintId() {
		return ePrintId;
	}

	public void setePrintId(long ePrintId) {
		this.ePrintId = ePrintId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Creator> getCreators() {
		return creators;
	}

	public void setCreators(List<Creator> creators) {
		this.creators = creators;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ePrintId ^ (ePrintId >>> 32));
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
		Article other = (Article) obj;
		if (ePrintId != other.ePrintId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return title + " - " + year;
	}

	@Override
	public int compareTo(Article arg0) {
		return this.title.compareTo(arg0.title);
	}
}
