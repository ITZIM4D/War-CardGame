
public class CardDeck {
	// Instance variables
	private CardNode head; // Head of the linked list
	private int size;      // Size of the linked list
	
	// Instantiate head to null when the object is created
	public CardDeck() {
		head = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void addCard(CardNode newCard) {
		CardNode currentCard;
		newCard.setNext(null);
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
		CardNode current = head;
		int count = 0;
		while (current != null) {
			if (count == pos) {
				return current;
			}
			count++;
			current = current.getNext();
		}
		return current;
	}
	
	// Delete node at a certain position (position 0 = head)
	public CardNode deleteNode(int pos) {
		CardNode temp = head;
		// If head is the node to be deleted
	    if (pos == 0) {
	        CardNode deletedNode = head;
	        head = head.getNext();
	        size--;
	        return deletedNode;
	    }
	    
	    // Traverse to the node just before the one to be deleted
	    for (int i = 0; i < pos - 1; i++) {
	        temp = temp.getNext();
	    }

	    // Get the node to be deleted
	    CardNode deletedNode = temp.getNext();

	    // Update the references to skip the deleted node
	    temp.setNext(deletedNode.getNext());

	    size--;

	    return deletedNode;
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
