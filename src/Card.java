
public class Card {
	private int suit;           // Integer value of card's suit
	private int value;          // Integer value of card
	private String suitString;  // String version of the suit value, Case sensitive
	private String valueString; // String version of the card value
	
	public Card(int value, int suit){
		// Set all variables to their starter/default values
		this.suit        = suit;
		this.value       = value;
		this.suitString  = suitToString();
		this.valueString = valueToString();
	}
	
	// Returns the integer value of the card suit
	// 1 = Clubs, 2 = Diamonds, 3 = Hearts, 4 = Spades (Alphabetical)
	public int getSuit() {
		return this.suit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String suitToString() {
		suitString = "";
		// Suit value == 1 is Clubs
		if(suit == 1) {
			suitString = "Clubs";
		} 
		// Suit value == 2 is Diamonds
		else if (suit == 2) {
			suitString = "Diamonds";
		} 
		// Suit value == 3 is Hearts
		else if (suit == 3) {
			suitString = "Hearts";
		} 
		// Suit value == 4 is Spades
		else if (suit == 4) {
			suitString = "Spades";
		}
		
		// Return string of card suit
		return suitString;
	}
	
	public String valueToString() {
		valueString = "";
		// Values of cards from 2 to Ace, Ace high 
		if (value == 2) {
			valueString = "Two";
		} else if (value == 3) {
			valueString = "Three";
		} else if (value == 4) {
			valueString = "Four";
		} else if (value == 5) {
			valueString = "Five";
		} else if (value == 6) {
			valueString = "Six";
		} else if (value == 7) {
			valueString = "Seven";
		} else if (value == 8) {
			valueString = "Eight";
		} else if (value == 9) {
			valueString = "Nine";
		} else if (value == 10) {
			valueString = "Ten";
		} else if (value == 11) {
			valueString = "Jack";
		} else if (value == 12) {
			valueString = "Queen";
		} else if (value == 13) {
			valueString = "King";
		} else if (value == 14) {
			valueString = "Ace";
		}
		
		// Return string of card value
		return valueString;
	}

	
	public String toString() {
		String displayString = valueString + " of " + suitString;
		
		return displayString;
	}
}
