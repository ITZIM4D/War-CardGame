import java.util.Random;

public class Game {
	CardDeck gameDeck;
	
	// Method to play the game
	public void playGame() {
		gameDeck = createDeck();
		gameDeck.displayDeck();
	}
	
	// Creates an ordered, unshuffled deck
	private CardDeck createDeck() {
		// Creates and instantiates a new CardDeck object
		CardDeck deck;
		deck = new CardDeck();
		
		// Adds each possible card to the deck
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				deck.addCard(new CardNode(new Card(j + 2, i + 1)));
			}
		}
		
		return deck;
	}
	
	private CardDeck shuffle(CardDeck deck) {
		// Create and instantiate variables
		CardDeck shuffledDeck = null; // Create a new CardDeck object to store the shuffled deck
		Random rand = new Random();   // instantiate an object to get random numbers
		int[] arr = new int[52];
		
		for(int i = 0; i < 52; i++) {
			
		}
		
		return shuffledDeck;
	}
	
	
}
