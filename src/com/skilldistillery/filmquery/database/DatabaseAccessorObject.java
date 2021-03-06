package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	
  @Override
  public Film findFilmById(int filmId) {
	  Film film = null;
	  
	  String user = "student";
	  String pass = "student";
	  
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT film.id, film.Title, Release_Year, film.rating, film.description, film.language_id, language.name, actor.first_name, actor.last_name "
				+ "FROM film JOIN language ON film.language_id = language.id "
				+ "JOIN film_actor ON film_actor.film_id = film.id "
				+ "JOIN actor ON film_actor.actor_id = actor.id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt("film.id"));
			film.setTitle(filmResult.getString("film.Title"));
			film.setReleaseYear(filmResult.getInt("Release_Year"));
			film.setRating(filmResult.getString("rating"));
			film.setDescription(filmResult.getString("description"));
			film.setLanguageId(filmResult.getInt("language_id"));
			film.setLanguage(filmResult.getString("language.name"));
			film.setActors(listOfActors(filmId));
		}
	} catch (SQLException e) {
		System.out.println("That selection is not available try again");
		e.printStackTrace();
	}
	return film;
  }
  
@Override
  public List<Film> findFilmByKeyword(String keyword) {
	List<Film> films = new ArrayList<>();
	
	  String user = "student";
	  String pass = "student";
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT film.id, film.title, film.description, release_year, film.language_id, language.name, "
	               + "rental_rate, film.length, replacement_cost, film.rating, special_features "
	               + "FROM film JOIN language ON film.language_id = language.id "
	               + "WHERE film.title LIKE ? OR film.description like ? ";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, "%"+keyword+"%");
	    stmt.setString(2, "%"+keyword+"%");
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
//	    	always null when i do film.set
//	    	film.setId(rs.getInt("film.id"));
//	    	film.setTitle(rs.getString("film.title"));
//	    	film.setDescription(rs.getString("film.description"));
//	    	film.setReleaseYear(rs.getInt("release_year"));
//			film.setLanguageId(rs.getInt("film.language_id"));
//			film.setLanguage(rs.getString("language.name"));
//			film.setLength(rs.getInt("length"));
//			film.setRating(rs.getString("rating"));
//			film.setSpecialFeatures(rs.getString("special_features"));
	    	int filmId = (rs.getInt("film.id"));
			String title = (rs.getString("film.title"));
			String desc = (rs.getString("film.description"));
			int releaseYear = (rs.getInt("release_year"));
			int langId = (rs.getInt("film.language_id"));
			String language = (rs.getString("language.name"));
			int length = rs.getInt("length");
			String rating = (rs.getString("rating"));
			String features = rs.getString("special_features");
//			String actors = rs.getString("film_list.actors");
			Film film = new Film(filmId, title, desc, releaseYear, langId, language,
                    length, rating, features, films, length); 
			film.setActors(listOfActors(filmId));
			films.add(film);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  		}
	    catch(NullPointerException e ) {
	        System.out.println("No films match keyword");;
	        }
	  return films;
	}
	public List<Actor> listOfActors(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String user = "student";
		String pass = "student";
		
		String sql = "SELECT * FROM film_actor " + "JOIN film ON film.id = film_actor.film_id "
			+ "JOIN actor on actor.id = film_actor.actor_id" + " WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}
}