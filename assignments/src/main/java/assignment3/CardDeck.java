package assignment3;

public class CardDeck {
	private Card[] deck;

	public CardDeck(int n) {
		if (n < 0 || n > 13) {
			throw new IllegalArgumentException("'n' must be in range 0-13");
		}

		int numberOfSuits = Card.Suit.values().length;
		int totalCards = n * numberOfSuits;

		this.deck = new Card[totalCards];

		for (int suitIndex = 0; suitIndex < numberOfSuits; suitIndex++) {
			int shift = n * suitIndex;

			Card.Suit suit = Card.Suit.values()[suitIndex];
			char suitChar = Card.SuitToChar(suit);

			for (int faceIndex = 0; faceIndex < n; faceIndex++) {
				Card.Face face = Card.Face.values()[faceIndex];
				int faceInt = Card.FaceToInt(face);

				this.deck[shift + faceIndex] = new Card(suitChar, faceInt);
			}
		}
	}

	public int getCardCount() {
		return this.deck.length;
	}

	public Card getCard(int index) {
		if (index < 0 || index > this.getCardCount()) {
			throw new IllegalArgumentException("'index' must be in range 0-" + (this.getCardCount() - 1));
		}

		return this.deck[index];
	}

	public void shufflePerfectly() {
		int half = this.getCardCount() / 2;

		Card[] shuffledDeck = new Card[this.getCardCount()];

		for (int index = 0; index < half; index++) {
			shuffledDeck[index * 2] = this.deck[index];
			shuffledDeck[index * 2 + 1] = this.deck[index + half];
		}

		this.deck = shuffledDeck;
	}
}
