package blackjack;

import java.util.ArrayList;

/*
Implementing the hand of cards both the Dealer and Player will have
 */
public class Hand {

    // Arraylist for the hand of cards
    private ArrayList<Card> hand;

    // Constructor
    public Hand() {
        this.hand = new ArrayList<Card>();
    }


    public void takeCardFromDeck(Deck deck) {
        /*
        Added takeCard method in class Deck
        Takes card from deckOfCards list to hand list
         */
        this.hand.add(deck.takeCard());
    }

    /*
     Using these as logic for ace display
     Public variable, so I can use it anywhere
     */
    private int plusTen = 9999;
    private int minusTen = -9999;

    @Override
    public String toString() {
        // Empty String output
        String handOutput = "";
        // Number of ace cards
        int aceCards = 0;
        for(Card card : hand) {
            // Adds the card to the String output
            handOutput += card;
            // if there are ace cards
            if(card.getValue() == 1) {
                // Increment number of ace cards
                aceCards++;

                /*
                Checking multiple conditions to add 1 to the end if it is applicable to display 11 instead of 1
                for the Hand. Uses the public int variables, which are reset to their original values after being checked,
                so that it can be checked again later.
                 */
                if((aceCards == 1) && (calculateHandValue() <= 21) && (hand.size() == 2)) {
                    handOutput += "1";
                }
                else if((aceCards == 1) && (calculateHandValue() <= 21) && (hand.size() == 3) && isPlusTen()) {
                    handOutput += "1";
                    if(isMinusTen()) {
                        // Checks again. Removes the 1 from before if true
                        handOutput = handOutput.substring(0, handOutput.length()-1);
                        minusTen = -9999; // Resets value
                    }
                }
                else if(aceCards >= 1 && calculateHandValue() <= 21 && isPlusTen()) {
                    handOutput += "1";
                    plusTen = 9999; // Resets value
                    if(isMinusTen()) {
                        // Removes the 1 from before
                        handOutput = handOutput.substring(0, handOutput.length()-1);
                        minusTen = -9999; // Resets value
                    }
                    if(aceCards > 1 && isPlusTen() && isMinusTen() && calculateHandValue() < 21) {
                        // Adds back the one in case all these conditions are true
                        handOutput += "1";
                        plusTen = 9999; // Resets value
                        minusTen = -9999; // Resets value
                        if(isMinusTen()) {
                            // Checks again. Removes the 1 from before if true
                            handOutput = handOutput.substring(0, handOutput.length()-1);
                            minusTen = -9999; // Resets value
                        }
                    }
                }
            }
            handOutput += "\n"; // New line for easier reading
        }
        return handOutput;
    }

    public int calculateHandValue() {
        // Total value of cards in hand
        int totalValue = 0;
        // Number of ace cards
        int aceCards = 0;

        // For each card in the hand list
        for(Card card : hand) {
            // Add the card's value to the hand's total value
            totalValue += card.getValue();
            // If there is an ace card
            if(card.getValue() == 1) {
                // Increment number of ace cards
                aceCards++;
            }
        }
        /*
        If the total value is less than 21 and there is at least an ace or more, add 10 to the total value since one ace
        would be worth 11. Then, if the total value goes over 21 (because of the +10) and there are ace cards, subtract 10 to counteract
        the ace value of 11 so that it stays under 21.
         */
        if(totalValue < 21 && aceCards >= 1) {
            totalValue += 10;
            plusTen = 10;
            if(totalValue > 21 && aceCards > 0) {
                totalValue -= 10;
                minusTen = -10;
            }
        }
        return totalValue;
    }

    // Gets first card for the dealer (in this class since both people have a hand)
    public Card getFirstCard(int index) {
        index = 0;
        return hand.get(0);
    }

    // Takes the cards in hand to a discard pile, then clears the hand
    public void discardHandToDeck(Deck discardedDeck){
        // Takes cards from hand to discardedDeck
        discardedDeck.addMultipleCards(hand);
        // clear the hand
        hand.clear();

    }

    // Checks if +10 happened
    public boolean isPlusTen() {
        if(plusTen == 10) {
            return true;
        }
        return false;
    }

    // Checks if -10 happened
    public boolean isMinusTen() {
        if(minusTen == -10) {
            return true;
        }
        return false;
    }


}
