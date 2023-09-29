// Node to create a linked list of cards

public class CardNode {
	// Instance variables
	private CardNode next; // Next card
	private Card data;     // Data = card object
	
	public CardNode(Card currentCard) {
		// Set next node to null
		this.next = null;
		this.data = currentCard;
	}
	
	// Returns the current card
	public Card getValue() {
		return data;
	}
	
	// Returns the value of the next card
	public CardNode getNext() {
		return next;
	}
	
	// Sets the next value of the current card
	public void setNext(CardNode newNext) {
		next = newNext;
	}
}
