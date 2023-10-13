import java.util.Random;
import java.util.Scanner;

public class Game {
	Scanner scan = new Scanner(System.in); // Scanner to get input from user
	CardDeck gameDeck;     // Deck that is being split between both hands
	CardDeck playerHand;   // Cards that player uses to play
	CardDeck computerHand; // Cards that computer uses to play
	int playerHandSize;    // Number of cards in player hand
	int computerHandSize;  // Number of cards in computer hand
	
	// Method to play the game
	public void playGame() {
		gameDeck = createDeck();
		gameDeck = shuffle(gameDeck);
		createHands();
		
		while(playerHand.getSize() > 0 && computerHand.getSize() > 0) {
			System.out.println("Press enter to battle");
			scan.nextLine();
			System.out.println("\n");
			battle();
		}
		
		// NEXT GO TO THE ELSE IN BATTLE AND MAKE THE TIEBREAKER
		
		
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
		CardDeck shuffledDeck = new CardDeck(); // Create a new CardDeck object to store the shuffled deck
		Random rand = new Random();             // Instantiate an object to get random numbers
		CardNode deletedNode;                   // Stores the node that gets deleted

		// stores a deleted node so that it cannot be repeated and adds the deleted node to a new deck
		for (int i = 52; i > 0; i--) {
			deletedNode = deck.deleteNode(rand.nextInt(i));
			shuffledDeck.addCard(deletedNode);
		}
		return shuffledDeck;
	}
	
	private void createHands() {
		playerHand = new CardDeck();
		computerHand = new CardDeck();
		CardNode nodeToAdd = null;
		for (int i = 0; i < 26; i++) {
			nodeToAdd = gameDeck.deleteNode(0);
			playerHand.addCard(nodeToAdd);
		}
		for (int i = 0; i < 26; i++) {
			nodeToAdd = gameDeck.deleteNode(0);
			computerHand.addCard(nodeToAdd);
		}
	}
	
	// Gets the top card of each hand and highest gets both cards and points
	// If it is a tie both sides place three cards and flips their fourth, highest gets all 10 cards
	public void battle() {
		Card playerCard;                // Play card flipped over by player
		Card computerCard;              // Play card flipped over by computer
		CardDeck pile = new CardDeck(); // Pile that all the cards stay in 
		int roundWinner;                // Winner of the current round that will get the cards back to their deck
		
		// Remove top card from player hand and from computer hand and put them in the pile
		playerCard = playerHand.deleteNode(0).getValue();
		computerCard = computerHand.deleteNode(0).getValue();
		pile.addCard(new CardNode(playerCard));
		pile.addCard(new CardNode(computerCard));
		
		System.out.println("Player flipped " + playerCard.toString());
		System.out.println("Computer flipped " + computerCard.toString());
		
		// Compare both cards
		// If the player's card has a higher value, give both cards to the player
		if (playerCard.getValue() > computerCard.getValue()) {
			// Display winner message
			System.out.println("Player has won the draw!");
			
			// Add both cards to the player's hand
			playerHand.addCard(pile.deleteNode(0));
			playerHand.addCard(pile.deleteNode(0));
		} 
		// Else if the computer's card has a higher value, give both cards to the computer
		else if (computerCard.getValue() > playerCard.getValue()) {
			// Display winner message
			System.out.println("Computer has won the draw!");
			
			// Add both cards to the computer's hand
			computerHand.addCard(pile.deleteNode(0));
			computerHand.addCard(pile.deleteNode(0));
		}
		// Else if it is a tie add 3 cards to the deck and flip the next one
		// If there is a tie and one or both sides do not have enough cards flip the last card that the deck with...
		// ...the least amount of cards has
		else {
			System.out.println("was a tie");
		}
		
		// Display the count of both the player and computer hands
		System.out.println("\nPlayer now has " + playerHand.getSize() + " cards left!");
		System.out.println("\nComputer now has " + computerHand.getSize() + " cards left!");
	}
}
