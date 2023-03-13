package blackjack;
/*
Using enum type to make constants for the card's rank (numerical value) for blackjack
I refer to these as numerical values, but they are typed as enum type
Noting that one and eleven are values based on ace, so it will not be constant
I will assign one to ace then work out the logic in another method
 */
public enum Rank {

    TWO("Two", 2),
    THREE("Three", 3),
    FOUR("Four", 4),
    FIVE("Five", 5),
    SIX("Six", 6),
    SEVEN("Seven", 7),
    EIGHT("Eight", 8),
    NINE("Nine", 9),
    TEN("Ten", 10),
    JACK("Jack", 10),
    KING("King", 10),
    QUEEN("Queen", 10),
    ACE("Ace",1);

    public final String rankLabel;
    public final int rankValue;

    // Constructor assigns arguments of a readable String name and integer value
    Rank(String rankLabel, int rankValue) {
        this.rankLabel = rankLabel;
        this.rankValue = rankValue;
    }

    @Override
    public String toString() {
        return rankLabel;
    }



}
