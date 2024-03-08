package assignment5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import assignment5.Card.Suit;

public class CardComparator implements Comparator<Card> {
    private boolean aceIsHighestFace;
    private Suit highestSuit;

    public CardComparator(boolean aceIsHighestFace, char highestSuit) {
        this.aceIsHighestFace = aceIsHighestFace;
        this.highestSuit = highestSuit != ' ' ? Card.CharToSuit(highestSuit) : null;
    }

    public int compare(Card cardA, Card cardB) {
        int cardAFaceWeight = getFaceWeight(cardA);
        int cardASuitWeight = getSuitWeight(cardA);

        int cardBFaceWeight = getFaceWeight(cardB);
        int cardBSuitWeight = getSuitWeight(cardB);

        if (cardASuitWeight == cardBSuitWeight) {
            return cardAFaceWeight - cardBFaceWeight;
        }

        return cardASuitWeight - cardBSuitWeight;
    }

    private int getFaceWeight(Card card) {
        if (card.face == Card.Face.ACE && this.aceIsHighestFace) {
            return 14;
        }

        return card.getFace();
    }

    private int getSuitWeight(Card card) {
        if (card.suit == this.highestSuit) {
            return 4;
        }

        return switch (card.suit) {
            case DIAMONDS -> 1;
            case SPADES -> 3;
            case HEARTS -> 2;
            case CLUBS -> 0;
        };
    }

    public static void main(String[] args) {
        Card s1 = new Card('S', 1);
        Card h1 = new Card('H', 1);
        Card d1 = new Card('D', 1);
        Card c1 = new Card('C', 1);
        Card s13 = new Card('S', 13);
        Card h13 = new Card('H', 13);
        Card d13 = new Card('D', 13);
        Card c13 = new Card('C', 13);
        ArrayList<Card> cards = new ArrayList<>(List.of(s1, s13, h1, h13, d1, d13, c1, c13));

        ArrayList<Card> expected = new ArrayList<Card>(Arrays.asList(c1, c13, h1, h13, s1, s13, d1, d13));
        Collections.sort(cards, new CardComparator(false, 'D'));

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getSuit() == expected.get(i).getSuit() &&
                    cards.get(i).getFace() == expected.get(i).getFace()) {
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }
        }
    }
}
