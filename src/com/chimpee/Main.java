package com.chimpee;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		Scanner scanner = new Scanner(System.in);

		while (game.getWrongLetters().size() != 10) {
			game.displayMessage(game.getEmptyTitleOfTheMovie(), game.getWrongLetters().size(), game.getWrongLetters());
			System.out.println("Guess a letter:");
			String guess = scanner.next().toLowerCase();

			while (guess.isEmpty() || guess.length() != 1 || guess.charAt(0) < 97 || guess.charAt(0) > 122
					|| game.getWrongLetters().contains(guess.charAt(0))) {
				System.out.println((game.getWrongLetters().contains(guess.charAt(0))
						? "You have already guessed this letter. Guess again:"
						: "It is not a letter! Guess again:"));
				guess = scanner.next().toLowerCase();
			}
			char c = guess.charAt(0);

			if (game.getTitleOfTheMovie().toLowerCase().contains(guess)) {
				game.setEmptyTitleOfTheMovie(
						game.replaceChar(game.getTitleOfTheMovie(), game.getEmptyTitleOfTheMovie(), c));
				String s = game.getTitleOfTheMovie().replace(" ", "_");
				if (game.getEmptyTitleOfTheMovie().compareToIgnoreCase(s) == 0) {
					break;
				}
			} else {
				game.getWrongLetters().add(c);
			}
		}
		scanner.close();

		System.out.println(game.getWrongLetters().size() == 10
				? ("You loose!\nYou've guessed 10 wrong letters. The correct answer is '" + game.getTitleOfTheMovie()
						+ "'.")
				: ("You win!\nYou have guessed '" + game.getTitleOfTheMovie() + "' correctly."));
	}
}
