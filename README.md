# GUESS WHO?

## Synopsis
This is a text based version of the classic board game Guess Who?

## Motivation
I built this for the final project of an intro to programming class. It demonstrates my current knowledge of Java.

## How to Run
Only the java file itself is necessary to play the game.

## Code Example
This method replaces a character's name in an array with a blank. The method has four parameters: an attribute, an array, an index number, and a boolean. The method combs through an array at a particular index searching for an attribute (string). The boolean value determines what to do when that attribute is found. Either the character's name will be replaced or kept. This method works for manipulating both the user's gameboard and the computer's gameboard.
```
public static void removeCharacters(String attribute, String[][] array, int arrayIndex, boolean bool) {
	for(int i = 0; i < array.length; i++) {
		if(array[i][arrayIndex].equals(attribute) == bool) {
			array[i][0] = "";
		}
	}
}		
```

## Tests
The checkQuestion method will return true if the user's input was a valid command. It will return false if the command was not valid, or if the command was valid but did not complete the player's turn.
```
@Test
	public void checkQuestion() {
		assertTrue(GuessWho.checkQuestion(characterArray, "MAN?"));
		assertTrue(GuessWho.checkQuestion(characterArray, "DAISY?"));
		assertTrue(GuessWho.checkQuestion(characterArray, "HAT?"));
		assertTrue(GuessWho.checkQuestion(characterArray, "BLACK HAIR?"));
		assertFalse(GuessWho.checkQuestion(characterArray, "MAN"));
		assertFalse(GuessWho.checkQuestion(characterArray, "man?"));
		assertFalse(GuessWho.checkQuestion(characterArray, "asdf"));
		assertFalse(GuessWho.checkQuestion(characterArray, "LIST"));
		assertFalse(GuessWho.checkQuestion(characterArray, "HELP"));
	}
```
