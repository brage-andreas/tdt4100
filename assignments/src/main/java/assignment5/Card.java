package assignment5;

import java.util.ArrayList;

public class Card implements Comparable<Card> {
    public Suit suit;
    public Face face;

    public static enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    public static enum Face {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public static char SuitToChar(Suit suit2) {
        return switch (suit2) {
            case DIAMONDS -> 'D';
            case SPADES -> 'S';
            case HEARTS -> 'H';
            case CLUBS -> 'C';
        };
    }

    public static Suit CharToSuit(char suit) throws IllegalArgumentException {
        return switch (suit) {
            case 'D' -> Suit.DIAMONDS;
            case 'S' -> Suit.SPADES;
            case 'H' -> Suit.HEARTS;
            case 'C' -> Suit.CLUBS;
            default -> throw new IllegalArgumentException("Invalid suit: " + suit);
        };
    }

    public static int FaceToInt(Face face) {
        return switch (face) {
            case ACE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
        };
    }

    public static Face IntToFace(int face) throws IllegalArgumentException {
        return switch (face) {
            case 1 -> Face.ACE;
            case 2 -> Face.TWO;
            case 3 -> Face.THREE;
            case 4 -> Face.FOUR;
            case 5 -> Face.FIVE;
            case 6 -> Face.SIX;
            case 7 -> Face.SEVEN;
            case 8 -> Face.EIGHT;
            case 9 -> Face.NINE;
            case 10 -> Face.TEN;
            case 11 -> Face.JACK;
            case 12 -> Face.QUEEN;
            case 13 -> Face.KING;
            default -> throw new IllegalArgumentException("Invalid face: " + face);
        };
    }

    public Card(char suit, int face) throws IllegalArgumentException {
        this.setSuit(suit);
        this.setFace(face);
    }

    public char getSuit() {
        return Card.SuitToChar(this.suit);
    }

    public int getFace() {
        return Card.FaceToInt(this.face);
    }

    public String toString() {
        return String.format("%s%s", this.getSuit(), this.getFace());
    }

    private void setSuit(char suit) throws IllegalArgumentException {
        this.suit = Card.CharToSuit(suit);
    }

    private void setFace(int face) throws IllegalArgumentException {
        this.face = Card.IntToFace(face);
    }

    public int compareTo(Card card) {
        ArrayList<Card> sortedCards = new ArrayList<Card>();
        sortedCards.add(this);
        sortedCards.add(card);

        sortedCards.sort((a, b) -> {
            int suitComparison = Character.compare(a.getSuit(), b.getSuit());
            int faceComparison = a.getFace() - b.getFace();

            if (suitComparison == 0 && faceComparison == 0) {
                return 0;
            }

            if (suitComparison == 0) {
                return faceComparison;
            }

            return suitComparison;
        });

        return sortedCards.get(0) == this ? -1 : 1;
    }
}
