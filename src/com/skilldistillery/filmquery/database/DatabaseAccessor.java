package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
//  public Film findFilmById(int filmId);
	public Film findFilmById(int filmId);

	public List<Film> findFilmByKeyword(String keyword);

//  public List<Actor> findActorsByFilmId(int filmId);
	
}
