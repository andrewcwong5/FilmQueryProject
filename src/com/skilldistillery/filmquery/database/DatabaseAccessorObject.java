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
		String sql = "SELECT id, Title, Release_Year, rating, description, language_id FROM film WHERE id = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("Title"));
			film.setReleaseYear(filmResult.getInt("Release_Year"));
			film.setRating(filmResult.getString("rating"));
			film.setDescription(filmResult.getString("description"));
			film.setLanguageId(filmResult.getInt("language_id"));
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
	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, "
	               +" rental_rate, length, replacement_cost, rating, special_features "
	               +  " FROM film "
	               + " WHERE title LIKE ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, "%"+keyword+"%");
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	int filmId = (rs.getInt(1));
			String title = (rs.getString("title"));
			String desc = (rs.getString("description"));
			int releaseYear = (rs.getInt("release_year"));
			int langId = (rs.getInt("language_id"));
			String rating = (rs.getString("rating"));
			int rentDur = rs.getInt("rental_duration");
	      double rate = rs.getDouble("rental_rate");
	      int length = rs.getInt("length");
	      double repCost = rs.getDouble("replacement_cost");
	      String features = rs.getString("special_features");
			Film film = new Film(filmId, title, desc, releaseYear, langId,
                    rentDur, rate, length, repCost, rating, features);
			films.add(film);
			
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  		}
	    catch(NullPointerException e ) {
	        e.printStackTrace();
	        }
	  return films;
	}
//	  Connection conn;
//	try {
//		conn = DriverManager.getConnection(URL, user, pass);
//		String sql = "SELECT id, Title, Release_Year, rating, description, Language_id FROM film WHERE title LIKE ?";
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setString(1,"%"+keyword+"%");
//		ResultSet filmResult = stmt.executeQuery();
//			if (filmResult.next()) {
//				film = new Film(); // Create the object
//				// Here is our mapping of query columns to our object fields:
//				film.setId(filmResult.getInt(1));
//				film.setTitle(filmResult.getString(2));
//				film.setReleaseYear(filmResult.getInt(3));
//				film.setRating(filmResult.getString(4));
//				film.setDescription(filmResult.getString(5));
//				film.setLanguageId(filmResult.getInt(6));
//			}
//		} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	
//	return film;
//  }
  public Film languageMatch(int languageId) {
	  Film filmDetails = null;
	  String user = "student";
	  String pass = "student";
	  
	  Connection conn;
	  try {
		  	conn = DriverManager.getConnection(URL, user, pass);
	  		String sql = "SELECT film.language_id, language.name "
	  				+ "FROM film JOIN language ON film.language_id = language.id";
	  		PreparedStatement stmt = conn.prepareStatement(sql);
	  		stmt.setInt(1, filmDetails.getLanguageId());
	  		ResultSet filmResult = stmt.executeQuery();
	  			if (filmResult.next()) {
	  				filmDetails = new Film(); // Create the object
	  				// Here is our mapping of query columns to our object fields:
	  				filmDetails.setLanguageId(filmResult.getInt(1));
	  				filmDetails.setLanguage(filmResult.getString(2));
	  			}
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		}
	  return filmDetails;
  }
  
  	@Override
  		public Actor findActorById(int actorId) {
  			// TODO Auto-generated method stub
  		return null;
}
}