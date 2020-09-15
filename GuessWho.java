/**
 * <p>This program is a text version of the Guess Who? game.</p>
 * <p>Created: 02.21.20</p>
 * @author Adam Grimshaw
 *
 */

package finalProject;

import java.util.Scanner;
import java.util.Arrays;
import java.util.jar.*;
import javax.tools.*;

public class GuessWho {

	static boolean endGame = false;

	/** This is the main method of the program. It defines characters and attributes, sets up and begins the game.
	 * @param args (String[]; this parameter is not used)
	 * */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// STARTING VALUES----------------------------------------------------------------------
		
		// Array of all characters and their attributes.
		String[][] characterArray = {{"LEROY", "brown", "He", "hat", "glasses", "beard"}, 
				{"SALLY", "blonde", "She", "no", "no", "no"},
				{"JO", "red", "She", "no", "no", "no"},
				{"TOM", "brown", "He", "hat", "glasses", "no"},
				{"MARIA", "black", "She", "no", "no", "no"},
				{"STAN", "brown", "He", "hat", "no", "no"},
				{"MARK", "brown", "He", "no", "no", "no"},
				{"HOPE", "brown", "She", "hat", "glasses", "beard"},
				{"TISHA", "black", "She", "hat", "glasses", "no"},
				{"DAISY", "blonde", "She", "hat", "no", "no"},
				{"HOLLY", "brown", "She", "no", "no", "no"},
				{"ANTONIO", "black", "He", "no", "glasses", "beard"},
				{"LEXY", "red", "He", "no", "glasses", "no"},
				{"GUY", "black", "He", "no", "no", "no"},
				{"MARSHA", "brown", "She", "no", "glasses", "no"},
				{"OLIVIA", "black", "She", "no", "no", "beard"},
				{"LEANNA", "red", "She", "hat", "no", "no"},
				{"SID", "brown", "He", "no", "no", "beard"},
				{"VINNY", "blonde", "He", "no", "no", "beard"},
				{"BUTCH", "blonde", "He", "no", "no", "no"},
				};
		
		// Questions the computer can ask.
		String[] computerQuestionsArray = {
			"Is your person a man?",
			"Does your person have brown hair?",
			"Does your person have a hat?",
			"Does your person wear glasses?",
			"Does your person have a beard?",
			"Does your person have black hair?",
			"Does your person have red hair?",
			"I'm really confused. Are you sure you answered my questions honestly?"
		};
		
		// Counter to track progression through computer's questions.
		int compTurnCounter = 0;
		
		
		// BEGIN GAME----------------------------------------------------------------------------
		
		// Initialize the game board. Selects random character for user and computer.
		shuffleArray(characterArray);
		
		// Arrays to keep track of user and computer progress.
		String[][] userArray = new String[characterArray.length][characterArray[0].length];
		String[][] computerArray = new String[characterArray.length][characterArray[0].length];
		for (int i = 0; i < characterArray.length; i++) {
			for (int j = 0; j < characterArray[i].length; j++) {
				userArray[i][j] = characterArray[i][j];
				computerArray[i][j] = characterArray[i][j];
			}
		}
		String[] userCharacter = characterArray[0];
		String[] computerCharacter = characterArray[1];
		String userQuestion = "";
		printInstructions();
		
		// Dev cheat
		//System.out.println("The computer's character is " + computerCharacter[0]);
		
		// Loop the game until finished.
		while(endGame == false) {
			lastCharacterComputer(computerArray);
			if (endGame == true) {
				return;
			}
			printUserCharacter(userCharacter);
			computerTurn(compTurnCounter, computerQuestionsArray, computerArray);
			compTurnCounter++;
			userTurn(userArray);
		}
	}
	
	/** Runs the computer's turn 
	 * @param turn (int; counter for how many turns the computer has had)
	 * @param qArray (String[]; feeds method questions to ask)
	 * @param array (String[][]; provides method with array of characters names and attributes)
	 * */
	public static void computerTurn(int turn, String[] qArray, String[][] array) {
		Scanner input = new Scanner(System.in);
		boolean turnComplete = false;
		String[] attributesArray = {"He", "brown", "hat", "glasses", "beard", "black", "red"};
		int[] attributesOrderArray = {2, 1, 3, 4, 5, 1, 1};
		while (turnComplete == false) {
			System.out.print(qArray[turn] + " ");
			String answer = input.next();
			if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) 	{
				// remove names of characters exhibiting that feature.
				removeCharacters(attributesArray[turn], array, attributesOrderArray[turn], false);
				turnComplete = true;
			}
			else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
				// remove names not exhibiting that feature.
				removeCharacters(attributesArray[turn], array, attributesOrderArray[turn], true);
				turnComplete = true;
			}
			else {
				System.out.println("Invalid input. Please answer Yes or No.");
			}
		}
		//printAllRemainingCharacters(array);
		//System.out.println();
	}
	
	/** Runs the user's turn.
	 * @param array (String[][]; provides method with array of characters names and attributes)
	 * */
	public static void userTurn(String[][] array) {
		Scanner input = new Scanner(System.in);
		boolean turnComplete = false;
		printAllRemainingCharacters(array);
		while(turnComplete == false) {
			System.out.print("Your turn. Enter a question: ");
			String question = input.nextLine();
			turnComplete = checkQuestion(array, question.toUpperCase());
		}
	}
	
	/** Creates a random variable and swaps array elements to randomize their order.
	 * @param array (String[][]; any two tier array to be shuffled)
	 * @return array (String[][]; returns shuffled array)
	 * */
	public static String[][] shuffleArray(String[][] array) {
		for (int i = 0; i < array.length; i++) {
			int j = (int)(Math.random() * (i + 1));
			String[] temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}

	/** Prints out instructions for how to play the game.*/
	public static void printInstructions() {
		System.out.print("WELCOME TO \"GUESS WHO?\"\n-----------------------\nYou and the computer will each be "
					+ "assigned a character. The goal is to guess the computer's character before "
					+ "it guesses yours.\nYou will be given the name and attributes of your "
					+ "character. Answer the computer's questions by typing either \"YES\" or "
					+ "\"NO\".\n\nOn your turn, ask the computer a question by typing one of the "
					+ "following:\n\"MAN?\", \"WOMAN?\", \"BROWN HAIR?\", \"BLONDE HAIR?\", "
					+ "\"BLACK HAIR?\", \"RED HAIR?\", \"GLASSES?\", \"HAT?\", \"BEARD?\", or "
					+ "\"(NAME OF CHARACTER)?\"\n\nFor a list of character attributes, type \"LIST ATTRIBUTES\". "
					+ "To see these instructions again, type \"INSTRUCTIONS\" or \"HELP\".\n\n"
					+ "The computer will go first. HAVE FUN!\n-------------------------------------\n\n");
	}
	
	/** Prints out a list of names of all the characters remaining on the user's board.
	 * @param array (String[][]; feeds method array of characters names and attributes)
	 * */
	public static void printAllRemainingCharacters(String[][] array) {
		System.out.println("The following characters are on the board:");
		// Creates a new array with the names in alphabetical order, so the user can't cheat.
		String[] alphaArray = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			alphaArray[i] = array[i][0];
		}
		Arrays.sort(alphaArray);
		// Print out the remaining characters.
		for(int i = 0; i < array.length; i++) {
			if (alphaArray[i].equals("")) {
				// Do nothing
			}
			else {
				System.out.print(alphaArray[i] + " ");
			}
		}
		System.out.print("\n\n");
	}
	
	/** Prints out name and attributes of user's character.
	 * @param array (String[]; feeds method an array of a single character's name and attributes)
	 * */
	public static void printUserCharacter(String[] array) {
		String hat = "does not wear a hat, ";
		String glasses = "does not have glasses, ";
		String beard = "and does not have a beard.";
		if (array[3] == "hat") {
			hat = "wears a hat, ";
		}
		if (array[4] == "glasses") {
			glasses = "wears glasses, ";
		}
		if (array[5] == "beard") {
			beard = "and has a beard.";
		}
		System.out.print("Your character is " + array[0] + ". ");
		System.out.println(array[2] + " has " + array[1] + " hair, " + hat + glasses + beard + "\n");
	}
	
	/** Prints out name and attributes of any character. Used for listing the attributes of all remaining characters.
	 * @param array (String[]; feeds method an array of a single character's name and attributes)
	 * */
	public static void printCharacter(String[] array) {
		String hat = "does not wear a hat, ";
		String glasses = "does not have glasses, ";
		String beard = "and does not have a beard.";
		if (array[3] == "hat") {
			hat = "wears a hat, ";
		}
		if (array[4] == "glasses") {
			glasses = "wears glasses, ";
		}
		if (array[5] == "beard") {
			beard = "and has a beard.";
		}
		System.out.print(array[0] + ": ");
		System.out.println(array[2] + " has " + array[1] + " hair, " + hat + glasses + beard + "\n");
	}
	
	/** Checks the user's question to see if it is valid. Then calls the removeCharacters method or ends the game.
	 * @param array (String[][]; feeds method an array of characters names and attributes)
	 * @param question (String; user's input)
	 * @return val (boolean; returns true if the input is valid AND if the program should not request the user to input another question.)
	 * */
	public static boolean checkQuestion(String[][] array, String question) {
		// If a name is entered, this checks to see if the name is the computer's character or if it is a valid name/entry.
		if (question.equals(array[1][0] + "?")) {
			question = question.substring(0, question.length() - 1);
			System.out.println("You guessed correctly! The computer's character is " + question + ". You win!");
			endGame = true;
			return true;
		}
		else if (question.equals(array[2][0] + "?") || question.equals(array[3][0] + "?") || question.equals(array[4][0] + "?") || question.equals(array[5][0] + "?") || question.equals(array[6][0] + "?") || question.equals(array[7][0] + "?") || question.equals(array[8][0] + "?") || question.equals(array[9][0] + "?") || question.equals(array[10][0] + "?") || question.equals(array[11][0] + "?") || question.equals(array[12][0] + "?") || question.equals(array[13][0] + "?") || question.equals(array[14][0] + "?") || question.equals(array[15][0] + "?") || question.equals(array[16][0] + "?") || question.equals(array[17][0] + "?") || question.equals(array[18][0] + "?") || question.equals(array[19][0] + "?")) {
			System.out.println("No");
			// Removes "?" from string before feeding to removeCharacters method.
			question = question.substring(0, question.length() - 1);
			removeCharacters(question, array, 0, true);
			return true;
		}
		// All other valid entries are defined in this switch.
		switch (question) {
		case "MAN?": 
			if (array[1][2].equalsIgnoreCase("He")) {
				System.out.println("Yes");
				removeCharacters("He", array, 2, false);
				return true;
			}
			else if (array[1][2].equalsIgnoreCase("She")) {
				System.out.println("No");
				removeCharacters("He", array, 2, true);
				return true;
			}
		case "WOMAN?": 
			if (array[1][2].equalsIgnoreCase("She")) {
				System.out.println("Yes");
				removeCharacters("She", array, 2, false);
				return true;
			}
			else if (array[1][2].equalsIgnoreCase("He")) {
				System.out.println("No");
				removeCharacters("She", array, 2, true);
				return true;
			}
		case "BROWN HAIR?": 
			if (array[1][1].equalsIgnoreCase("brown")) {
				System.out.println("Yes");
				removeCharacters("brown", array, 1, false);
				return true;
			}
			else if (array[1][1].equalsIgnoreCase("blonde") || array[1][1].equalsIgnoreCase("red") || array[1][1].equalsIgnoreCase("black")) {
				System.out.println("No");
				removeCharacters("brown", array, 1, true);
				return true;
			}
		case "BLONDE HAIR?": 
			if (array[1][1].equalsIgnoreCase("blonde")) {
				System.out.println("Yes");
				removeCharacters("blonde", array, 1, false);
				return true;
			}
			else if (array[1][1].equalsIgnoreCase("brown") || array[1][1].equalsIgnoreCase("red") || array[1][1].equalsIgnoreCase("black")) {
				System.out.println("No");
				removeCharacters("blonde", array, 1, true);
				return true;
			}
		case "BLACK HAIR?": 
			if (array[1][1].equalsIgnoreCase("black")) {
				System.out.println("Yes");
				removeCharacters("black", array, 1, false);
				return true;
			}
			else if (array[1][1].equalsIgnoreCase("blonde") || array[1][1].equalsIgnoreCase("red") || array[1][1].equalsIgnoreCase("brown")) {
				System.out.println("No");
				removeCharacters("black", array, 1, true);
				return true;
			}
		case "RED HAIR?": 
			if (array[1][1].equalsIgnoreCase("red")) {
				System.out.println("Yes");
				removeCharacters("red", array, 1, false);
				return true;
			}
			else if (array[1][1].equalsIgnoreCase("blonde") || array[1][1].equalsIgnoreCase("brown") || array[1][1].equalsIgnoreCase("black")) {
				System.out.println("No");
				removeCharacters("red", array, 1, true);
				return true;
			}
		case "GLASSES?": 
			if (array[1][4].equalsIgnoreCase("glasses")) {
				System.out.println("Yes");
				removeCharacters("glasses", array, 4, false);
				return true;
			}
			else if (array[1][4].equalsIgnoreCase("no")) {
				System.out.println("No");
				removeCharacters("glasses", array, 4, true);
				return true;
			}
		case "HAT?": 
			if (array[1][3].equalsIgnoreCase("hat")) {
				System.out.println("Yes");
				removeCharacters("hat", array, 3, false);
				return true;
			}
			else if (array[1][3].equalsIgnoreCase("no")) {
				System.out.println("No");
				removeCharacters("hat", array, 3, true);
				return true;
			}
		case "BEARD?": 
			if (array[1][5].equalsIgnoreCase("beard")) {
				System.out.println("Yes");
				removeCharacters("beard", array, 5, false);
				return true;
			}
			else if (array[1][5].equalsIgnoreCase("no")) {
				System.out.println("No");
				removeCharacters("beard", array, 5, true);
				return true;
			}
		case "INSTRUCTIONS":
			printInstructions();
			return false;
			
		case "HELP":
			printInstructions();
			return false;
			
		case "LIST ATTRIBUTES":
			for (int i = 0; i < array.length; i++) {
					if (array[i][0].equals("")) {
						// do nothing
					}
					else {
						printCharacter(array[i]);
					}
				}
			return false;
		case "LIST":
			System.out.println();
			for (int i = 0; i < array.length; i++) {
					if (array[i][0].equals("")) {
						// do nothing
					}
					else {
						printCharacter(array[i]);
					}
				}
			return false;
		}
		System.out.println("Invalid input. Please try again.");
		return false;
	}
	
	/** Removes characters from user/computer array.
	 * @param attribute (String; attribute to search for in array)
	 * @param array (String[][]; array to be searched)
	 * @param arrayIndex (int; which part of the array to be searched)
	 * @param bool (boolean; defines if all characters expressing that attribute should have their names kept or eliminated from the array)
	 * */
	public static void removeCharacters(String attribute, String[][] array, int arrayIndex, boolean bool) {
		for(int i = 0; i < array.length; i++) {
			if(array[i][arrayIndex].equals(attribute) == bool) {
				array[i][0] = "";
			}
		}
		System.out.println();
	}
	
	/** Checks the computer's array of characters to see if only one character is left. If so, it guesses that character. 
	 * The game ends either with a win for the computer or an accusation of cheating.
	 * @param array (String[][]; array to search for character name)
	 * */
	public static void lastCharacterComputer(String[][] array) {
		Scanner input = new Scanner(System.in);
		boolean turnComplete = false;
		int counter = 0;
		String character = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i][0].equals("")) {
				// do nothing
			}
			else {
				character = array[i][0];
				counter++;
			}
		}
		if (counter == 1) {
			// guess character
			System.out.print("Is your character " + character + "? ");
			String answer = input.next();
			if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
				System.out.println("Computer wins!");
				endGame = true;
				turnComplete = true;
			}
			else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
				System.out.println("Really?! I'm pretty sure your person is " + character + ".");
				System.out.println("\nNobody wins when somebody cheats.\nGAME OVER");
				endGame = true;
				turnComplete = true;
			}
			else {
				System.out.println("Invalid input. Please answer Yes or No.");
				lastCharacterComputer(array);
			}
		}
	}
}
