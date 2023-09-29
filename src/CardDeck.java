
public class CardDeck {
	// Instance variables
	private CardNode head; // Head of the linked list
	private int size;      // Size of the linked list
	
	// Instantiate head to null when the object is created
	public CardDeck() {
		head = null;
		size = 0;
	}
	
	public void addCard(CardNode newCard) {
		CardNode currentCard;
		if (head == null) {
			head = newCard;
		}
		else {
			currentCard = head;
			while(currentCard.getNext() != null) {
				currentCard = currentCard.getNext();
			}
			currentCard.setNext(newCard);
		}
		size++;
	}
	
	// Returns the card node from a certain position in the deck
	public CardNode getNode(int pos) {
		CardNode node;
		node = head;
		for (int i = 0; i < pos; i++) {
			node = node.getNext();
		}
		return node;
	}
	
	// Delete node at a certain position (position 1 = head)
	public void deleteNode(int pos) {
		CardNode temp = head;
		
		// If head is the node to be deleted
		if(pos == 1) {
			head = head.getNext();
		}
		
		
	}
	
	// Displays the each cardNode of the deck
	public void displayDeck() {
		CardNode currNode;
		for (int i = 0; i < size; i++) {
			currNode = getNode(i);
			System.out.println(currNode.getValue().toString());
		}
	}
	
	
	
}
