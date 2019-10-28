package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
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
	   		int filmId = 1;
	   		while (filmId !=0) {
	   			try {
	   				System.out.println("What is the film ID? (press 0 to exit)");
					filmId = input.nextInt();
					
				} catch (InputMismatchException e) {
					System.out.println("Not valid input, try again.");
					filmId = input.nextInt();
				}
					
					Film film = db.findFilmById(filmId);
					if (film == null) {
						System.out.println("invalid film selection try again");
					}
					else System.out.println(film);
	   		}
	   			
	   		System.out.println("end of film id lookup");
	   		break;
	   		
	   	case 2: 
	   		String keyword;
	   		do {
	   			System.out.println("Enter keyword to search for film(press 0 to end): ");
	   			keyword = input.next();
	   			List<Film> films = db.findFilmByKeyword(keyword);
	   			if (films.size() == 0) {
	   				System.out.println("No films matched you keyword selection, try again");
	   			}
	   				else {
	   					System.out.println(films);
	   					System.out.println(films.size()+" Matches");
	   				}
	   			
	    	} while (!keyword.equals( "0"));
	   		System.out.println("end of film id lookup");
	   		break;
	   	
	   	case 3:
	   		//quit
	   		System.out.println("Have a good day");
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
