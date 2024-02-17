package assignment3;

import assignment3.Card.Face;
import assignment3.Card.Suit;

public class CardDeck {
	private Card[] deck;

	public CardDeck(int n) {
		if (n < 0 || n > 13) {
			throw new IllegalArgumentException("'n' must be in range 0-13");
		}

		Suit[] suits = Suit.values();
		Face[] faces = Face.values();

		int numberOfSuits = suits.length;
		int totalNumberOfCards = n * numberOfSuits;

		this.deck = new Card[totalNumberOfCards];

		for (int suitIndex = 0; suitIndex < numberOfSuits; suitIndex++) {
			int faceIndexShift = n * suitIndex;

			Suit suit = suits[suitIndex];
			char suitChar = Card.SuitToChar(suit);

			for (int faceIndex = 0; faceIndex < n; faceIndex++) {
				Face face = faces[faceIndex];
				int faceInt = Card.FaceToInt(face);

				this.deck[faceIndexShift + faceIndex] = new Card(suitChar, faceInt);
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
		int centerIndex = this.getCardCount() / 2;

		Card[] shuffledDeck = new Card[this.getCardCount()];

		for (int index = 0; index < centerIndex; index++) {
			shuffledDeck[index * 2] = this.deck[index];
			shuffledDeck[index * 2 + 1] = this.deck[index + centerIndex];
		}

		this.deck = shuffledDeck;
	}
}
