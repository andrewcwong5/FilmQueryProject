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
		String sql = "SELECT film.id, Title, Release_Year, rating, description, film.language_id, language.name "
				+ "FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt("film.id"));
			film.setTitle(filmResult.getString("Title"));
			film.setReleaseYear(filmResult.getInt("Release_Year"));
			film.setRating(filmResult.getString("rating"));
			film.setDescription(filmResult.getString("description"));
			film.setLanguageId(filmResult.getInt("language_id"));
			film.setLanguage(filmResult.getString("language.name"));
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
	    String sql = "SELECT film.id, title, description, release_year, film.language_id, language.name, rental_duration, "
	               +" rental_rate, length, replacement_cost, rating, special_features "
	               +  " FROM film JOIN language ON film.language_id = language.id"
	               + " WHERE title LIKE ? OR description like ? ";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, "%"+keyword+"%");
	    stmt.setString(2, "%"+keyword+"%");
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	int filmId = (rs.getInt("film.id"));
			String title = (rs.getString("title"));
			String desc = (rs.getString("description"));
			int releaseYear = (rs.getInt("release_year"));
			int langId = (rs.getInt("film.language_id"));
			String language = (rs.getString("language.name"));
			int length = rs.getInt("length");
			String rating = (rs.getString("rating"));
	      String features = rs.getString("special_features");
			Film film = new Film(filmId, title, desc, releaseYear, langId, language,
                    length, rating, features, films, length);
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
}