package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {

	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private String language;
	private int length;
	private String rating;
	private String specialFeatures;
	private List<Film> films;
	private double rate;
	private String actor;

	public Film(int id, String title, String description, int releaseYear, int languageId, String language, int length,
			String rating, String specialFeatures, List<Film> films, double rate, String actor) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.language = language;
		this.length = length;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.films = films;
		this.rate = rate;
		this.actor = actor;
	}

	public Film() {
		super();
	}

	public Film(int id, String title, String description, int releaseYear, int languageId, String language, int length,
			String rating, String specialFeatures, List<Film> films, double rate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.language = language;
		this.length = length;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.films = films;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ",\nreleaseYear=" + releaseYear
				+ ", language=" + language + ", rating=" + rating + ", actors=" + actor +"]\n\n";
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

}
