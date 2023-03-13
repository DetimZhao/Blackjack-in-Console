package blackjack;
/*
Using enum type to make constants for the card's suit (categories in which the cards are divided into)
 */
public enum Suit {

    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    SPADES("Spades"),
    HEARTS("Hearts");

    private final String suitLabel;

    // Constructor assigns arguments of a readable String name
    Suit(String suitLabel) {
        this.suitLabel = suitLabel;
    }

    @Override
    public String toString() {
        return this.suitLabel;
    }



}
