package com.skilldistillery.filmquery.database;

import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

  @Override
  public Film findFilmById(int filmId) {
    return null;
    //dont return null, 
  }
//to do
//public Actor findActorById(int actorId);
//public List<Actor> findActorsByFilmId(int filmId) { };
}