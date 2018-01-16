package com.chimpee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private String titleOfTheMovie; // the title that the player has to guess
	private ArrayList<Character> wrongLetters; // this contains the letters, that the player has already asked.
	private String emptyTitleOfTheMovie = "";

	public Game() {
		this.wrongLetters = new ArrayList<>();
		this.titleOfTheMovie = randomMovieTitleGenerator();
		for (int i = 0; i < this.titleOfTheMovie.length(); i++) {
			this.emptyTitleOfTheMovie += "_";
		}
		System.out.println("Guess the movie");
		System.out.println();
	}

	/** It is open a text file and give back a random title from the movies.txt */
	private String randomMovieTitleGenerator() {
		String movieTitle = "";
		File file = new File("movies.txt");
		ArrayList<String> movies = new ArrayList<>();
		try (Scanner scanner = new Scanner(file);) {
			while (scanner.hasNextLine()) {
				String title = scanner.nextLine();
				movies.add(title);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int size = movies.size();
		if (size != 0) {
			int randomNumber = randomNumberGenerator(size);
			movieTitle = movies.get(randomNumber);
		}
		return movieTitle;
	}

	/** Generate a random integer number between 0 and the size */
	private int randomNumberGenerator(int size) {
		return (int) (Math.random() * size);
	}

	/**
	 * change a letter in an empty string, that index where we can find the letter
	 * in the original string
	 */
	public String replaceChar(String title, String emptyTitle, char replaceChar) {
		char[] chars = emptyTitle.toCharArray();
		String lowercaseTitle = title.toLowerCase();
		for (int i = 0; i < title.length(); i++) {
			if (lowercaseTitle.charAt(i) == replaceChar) {
				chars[i] = title.charAt(i);
			}
		}
		return String.valueOf(chars);
	}

	public void displayMessage(String guessTitle, int wrongGuesses, ArrayList<Character> wrongLetters) {
		System.out.println("You are guessing: " + guessTitle);
		System.out.print("You have guessed (" + wrongLetters.size() + ") wrong letters:");
		for (int i = 0; i < wrongLetters.size(); i++) {
			System.out.print(wrongLetters.get(i) + " ");
		}
		System.out.println();
	}

	// getters
	public String getTitleOfTheMovie() {
		return this.titleOfTheMovie;
	}

	public String getEmptyTitleOfTheMovie() {
		return emptyTitleOfTheMovie;
	}

	public ArrayList<Character> getWrongLetters() {
		return wrongLetters;
	}


	// setters
	public void setTitleOfTheMovie(String titleOfTheMovie) {
		this.titleOfTheMovie = titleOfTheMovie;
	}

	public void setWrongLetters(ArrayList<Character> wrongLetters) {
		this.wrongLetters = wrongLetters;
	}

	public void setEmptyTitleOfTheMovie(String emptyTitleOfTheMovie) {
		this.emptyTitleOfTheMovie = emptyTitleOfTheMovie;
	}

}
