package blackjack;

import java.util.ArrayList;
import java.util.Collections;

/*
Implementing the deck of cards that will be used for the game
 */
public class Deck {

    // ArrayList since its size can change unlike an Array
    private ArrayList<Card> deckOfCards;

    // Constructor to make object of ArrayList with Card type (enum)
    public Deck() {
         this.deckOfCards = new ArrayList<Card>();
    }

    // Generates a deck
    public void createDeckOfCards() {
        // Enhanced for loop: For every suit in Suit enum class, return the values in Suit enum
        for(Suit cardSuit : Suit.values()) {
            // For every value in Rank enum class, return values the values in Rank enum
            for(Rank cardValue : Rank.values()) {
                // Refers to ArrayList, adding a new card which points to constructor in Card class
                this.deckOfCards.add(new Card(cardSuit, cardValue));   
            }
        }
    }

    // returns the arraylist containing all the cards in this deck (getting all the cards)
    public ArrayList<Card> getCards() {
        return deckOfCards;
    }

    // Adds a single card to deck (for testing purposes) or for a discard pile later
    public void addCardToDeck(Card card) {
        deckOfCards.add(card);
    }

    // Shuffles deck
    public void shuffleDeck() {
        // Shuffles/Randomizes elements in Arraylist Deck using shuffle from Collections
        Collections.shuffle(deckOfCards);
    }


    // Using toString to return the arraylist as a string
    @Override
    public String toString() {
        // string var to hold the cards
        String cardStr = "";
        // for each card in the deck
        for(Card card : this.deckOfCards) {
             cardStr += card + "\n";
        }
        return cardStr;
    }

    // Takes top card from deck, and removes it from the deck
    public Card takeCard() {
        // Copies first card from deck and calls on constructor in Card class
        Card cardToTake = new Card(deckOfCards.get(0));
        // Removes the same card from the deck of cards
        deckOfCards.remove(0);
        // returns the Card back to hand
        return cardToTake;
    }

    // Makes sure the Deck has cards
    public boolean hasCards() {
        if (deckOfCards.size()>0){
            return true;
        }
        return false;
    }

    // Empties out the deck when needed
    public void emptyDeck() {
        deckOfCards.clear();
    }

    // Passes in cards to add to the current deck
    public void addMultipleCards(ArrayList<Card> cards) {
        deckOfCards.addAll(cards);
    }

    /*
    Take all the cards in the discarded pile and add them into this deck
    Making sure to empty the old one and shuffle the new
     */
    public void createDeckFromDiscards(Deck discards) {
        this.addMultipleCards(discards.getCards());
        this.shuffleDeck();
        discards.emptyDeck();
        System.out.println("--> You have ran out of cards. A new shuffled deck from the discard pile will be created.\n");
    }

    public int cardsLeft() {
        return deckOfCards.size();
    }


}
