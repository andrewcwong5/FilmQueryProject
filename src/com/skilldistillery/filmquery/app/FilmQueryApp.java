package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    
    app.launch();
  }

  private void test(int filmId) {
	
    Film film = db.findFilmById(filmId);
    
    	if (film == null) {
    		System.out.println("invalid film selection try again");
    	}
    	else System.out.println(film);
    	
    	
//    Film filmDetails = db.languageMatch(film.getLanguageId());	
  
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    menu();
    
    startUserInterface(input);
    
    input.close();
  }

  
  	private void startUserInterface(Scanner input) {
  		int choice = input.nextInt();
	  
	  switch (choice) {
	   	case 1: 
	   		int filmId;
	   		do {
	   			System.out.println("What is the film ID? ");
	   			filmId = input.nextInt();
	   			test(filmId);
	   		} while (filmId !=0);
	   		System.out.println("end of film id lookup");
	   		break;
	   		
	   	case 2: 
	   		String keyword;
	   		do {
	   			System.out.println("Enter keyword to search for film: ");
	   			keyword = input.next();
	   			List<Film> films = db.findFilmByKeyword(keyword);
	   			if (films == null) {
	   				System.out.println("invalid film selection try again");
	   			}
	   				else System.out.println(films);
	    	} while (keyword != "0");
	   		System.out.println("end of film id lookup");
	   		break;
	   	
	   	case 3:
	   		//quit
	   		break;
	   		
	   	default:
	   		break;
	  }
  	}
  
  private void menu() {
	  System.out.println("1) Look up a film by its id: ");
	  System.out.println("2) Look up a film by a search keyword: ");
	  System.out.println("3) Exit the application ");
  }
}
