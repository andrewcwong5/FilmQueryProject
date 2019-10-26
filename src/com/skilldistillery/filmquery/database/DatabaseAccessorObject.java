package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "SELECT id, Title, Release_Year, rating, description FROM film WHERE id = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt(1));
			film.setTitle(filmResult.getString(2));
			film.setReleaseYear(filmResult.getInt(3));
			film.setRating(filmResult.getString(4));
			film.setDescription(filmResult.getString(5));
		}
	} catch (SQLException e) {
		System.out.println("That selection is not available try again");
		e.printStackTrace();
	}
	return film;
  }
  
  @Override
  public Film findFilmByKeyword(String keyword) {
	  Film film = null;
	  String user = "student";
	  String pass = "student";
	  
	  Connection conn;
	try {
		conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT id, Title, Release_Year, rating, description FROM film WHERE title LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,keyword);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setId(filmResult.getInt(1));
			film.setTitle(filmResult.getString(2));
			film.setReleaseYear(filmResult.getInt(3));
			film.setRating(filmResult.getString(4));
			film.setDescription(filmResult.getString(5));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return film;
  }
  
//Stretch	
//to do
//public Actor findActorById(int actorId);
//public List<Actor> findActorsByFilmId(int filmId) { };
  
  @Override
  	public Actor findActorById(int actorId) {
		  Actor actor = null;
		  String user = "student";
		  String pass = "student";
		  
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
//		    actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
  }
}
