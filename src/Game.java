import java.util.Random;
import java.lang.Math;
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
		
		// Create a deck and shuffle it ten times for better probabilities
		gameDeck = createDeck();
		for (int i = 0; i < 10; i++) {
			gameDeck = shuffle(gameDeck);
		}
		createHands();
		
		
		while(playerHand.getSize() > 0 && computerHand.getSize() > 0) {
			System.out.println("Press enter to battle");
			scan.nextLine();
			System.out.println("\n");
			battle();
		}
		
		if (playerHand.getSize() > computerHand.getSize()) {
			System.out.println("Player has won the match! Congratulations!");
		}
		else if (computerHand.getSize() > playerHand.getSize()) {
			System.out.println("Computer has won the match! Try again next time!");
		}
		
		
		
		
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
		Card playerTiebreaker;          // The player's card that will be compared for the tie breaker 
		Card computerTiebreaker;        // The computer's card that will be compared for the tie breaker
		CardNode playerTieNode;         // The player's card node that will be added to the pile
		CardNode computerTieNode;       // The computer's card node that will be added to the pile
		int pileSize;                   // Size of the pile at hand
		int maxSize;                    // Size of the hand that is the smallest so it is the max of the tiebreaker pile size

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
			System.out.println("It was a tie!");
			
			boolean tie = true;
			// While there is no tie
			while (tie == true) {
				System.out.println("Press enter to break the tie");
				scan.nextLine();
				System.out.println("\n");
				
				// If both hands have enough cards for a normal tie breaker
				if (playerHand.getSize() >= 4 && computerHand.getSize() >= 4) {
					// Get the fourth card of the player hand to break the tie
					for (int i = 0; i < 3; i++) {
						pile.addCard(playerHand.deleteNode(0));
					}
					playerTieNode = playerHand.deleteNode(0);
					playerTiebreaker = playerTieNode.getValue();
					pile.addCard(playerTieNode);
					
					// Display card value
					System.out.println("Player flipped over 3 cards and their tiebreaker card is " + playerTiebreaker.toString());

					for (int i = 0; i < 3; i++) {
						pile.addCard(computerHand.deleteNode(0));
					}
					computerTieNode = computerHand.deleteNode(0);
					computerTiebreaker = computerTieNode.getValue();
					pile.addCard(computerTieNode);
					
					// Display card value
					System.out.println("Computer flipped over 3 cards and their tiebreaker card is " + computerTiebreaker.toString());
					
					// If the computer wins the tie
					if (computerTiebreaker.getValue() > playerTiebreaker.getValue()) {
						// Set tie to false to end while loop
						tie = false;
						
						// Display winner message
						System.out.println("Computer has won the tie!");
						
						pileSize = pile.getSize();
						
						// Add all the cards from the pile to computer hand
						for(int i = 0; i < pileSize; i++) {
							computerHand.addCard(pile.deleteNode(0));
						}
					}
					// Else if the player wins the tie
					else if (computerTiebreaker.getValue() < playerTiebreaker.getValue()) {
						// Set tie to false to end while loop
						tie = false;

						
						// Display winner message
						System.out.println("Player has won the tie!");
						
						// Add all the cards from the pile to player hand
						pileSize = pile.getSize();
						for(int i = 0; i < pileSize; i++) {
							playerHand.addCard(pile.deleteNode(0));
						}
					}
					// Else if it is another tie
					else {
						// Keep tie to true to continue while loop
						tie = true;
					}
				}
				// Else if one hand or the other doesn't have enough
				else if (playerHand.getSize() <= 3 || computerHand.getSize() <= 3) {
					maxSize = Math.min(playerHand.getSize(), computerHand.getSize());
					
					// Get the xth (maxSize) card of the the player hand to break the tie
					for (int i = 0; i < maxSize - 1; i++) {
						pile.addCard(playerHand.deleteNode(0));
					}
					playerTieNode = playerHand.deleteNode(0);
					playerTiebreaker = playerTieNode.getValue();
					pile.addCard(playerTieNode);
					
					// Display card value
					System.out.println("Player flipped over " + maxSize + " cards and their tiebreaker card is " + playerTiebreaker.toString());
					
					// Get the xth (maxSize) card of the computer hand to break the tie
					for (int i = 0; i < maxSize - 1; i++) {
						pile.addCard(computerHand.deleteNode(0));
					}
					computerTieNode = computerHand.deleteNode(0);
					computerTiebreaker = computerTieNode.getValue();
					pile.addCard(computerTieNode);
					
					// Display card value
					System.out.println("Player flipped over " + maxSize + " cards and their tiebreaker card is " + computerTiebreaker.toString());	
					
					// If the computer wins the tie
					if (computerTiebreaker.getValue() > playerTiebreaker.getValue()) {
						// Set tie to false to end while loop
						tie = false;
						
						// Display winner message
						System.out.println("Computer has won the tie!");
						
						pileSize = pile.getSize();
						
						// Add all the cards from the pile to computer hand
						for(int i = 0; i < pileSize; i++) {
							computerHand.addCard(pile.deleteNode(0));
						}
					}
					// Else if the player wins the tie
					else if (computerTiebreaker.getValue() < playerTiebreaker.getValue()) {
						// Set tie to false to end while loop
						tie = false;

						
						// Display winner message
						System.out.println("Player has won the tie!");
						
						// Add all the cards from the pile to player hand
						pileSize = pile.getSize();
						for(int i = 0; i < pileSize; i++) {
							playerHand.addCard(pile.deleteNode(0));
						}
					}
					// Else if it is another tie
					else {
						// Keep tie to true to continue while loop
						tie = false;
						
						if (playerHand.getSize() > computerHand.getSize()) {
							System.out.println("Player has run out of cards");
						}
						else {
							System.out.println("Computer has run out of cards");
						}
					}
				}
					
					
			}
		}
		
		// Display the count of both the player and computer hands
		System.out.println("\nPlayer now has " + playerHand.getSize() + " cards left!");
		System.out.println("Computer now has " + computerHand.getSize() + " cards left!\n");
	}
}
