package blackjack;

/*
Implementing Card type
Has a suit, value, constructor, toString method, getValue method
 */
public class Card {

    /*
    Only four suits (Club, Diamond, Spade, Heart) possible made by enum type Suit
     */
    private Suit suit;

    /*
    Rank/numerical value of a card where Ace: 1 (can be 11 but logic is for later), Jack-King (Face cards): 2-10
     */
    private Rank rankName;
    private Rank valueNum;

    /*
    Making a parameterized/overloaded constructor for the Card object
    Arguments require the card's suit and rank
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit; //suit of the card
        this.rankName = rank; //rank of the card (name)
        this.valueNum = rank; // also the rank of the card (but for num)

    }

    // Another overloaded constructor to fit the takeCard method in Deck class
    public Card(Card card) {
        this.suit = card.getSuit();
        this.rankName = card.getRank();
        this.valueNum = card.getRank();
    }

    // returns the suit of the Card (Getter)
    public Suit getSuit() {
        return this.suit;
    }

    // returns the rank name of the Card (Getter)
    public Rank getRank() {
        return this.rankName;
    }

    // returns the int value of the Card (Getter)
    public int getValue() {
        return this.valueNum.rankValue;
    }


    // Using toString to return the name and value of the Card
    @Override
    public String toString() {
        //returns the value and suit (as a string object) of the Card in the form of "value of suit"
            return this.rankName + " of " + this.suit + " - " + getValue();

    }


}

