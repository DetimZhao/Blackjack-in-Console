package blackjack;

/*
This class will be used in both the subclasses of the Player and Dealer, so most of the methods here will be utilized in both
Parent class for Player and Dealer
 */
public class Person {

    private String name;
    private Hand hand;

    // Makes empty Hand object and name
    public Person() {
        this.hand = new Hand();
        this.name = "";
    }

    // Gets person's hand
    public Hand getHand() {
        return this.hand;
    }

    // Sets person's hand (for testing)
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    // Gets person's name (for testing)
    public String getName() {
        return this.name;
    }

    // Sets person's name (for testing)
    public void setName(String name) {
        this.name = name;
    }

    // Hit option when playing the game (adds card to one's hand) (for testing)
    public void hitOption(Deck deck, Deck discard) {
        hand.takeCardFromDeck(deck);
    }

    // Checks if the hand is black jack
    public boolean hasBlackjack() {
        if(getHand().calculateHandValue() == 21) {
            // true if the hand's value equals 21
            return true;
        }
        return false;
    }

    // Prints out the hand of the person
    public void printHand() {
        System.out.printf("%s's hand: \n", this.name);
        System.out.println("----------------------");
        System.out.print(hand);
        System.out.println("Current Hand Value: " + this.hand.calculateHandValue() + "\n");
    }

    /*
    Since both the Dealer and Player can hit, it is in this class
    This method will handle the aspect of hitting
     */
    public void hit(Deck deck, Deck discards) {
        // If the deck does not have cards
        if(!deck.hasCards()) {
            System.out.println("\n");
            deck.createDeckFromDiscards(discards);
        }
        // Takes card from deck to add to hand
        this.hand.takeCardFromDeck(deck);
        System.out.println("\n| " + this.name + " hits. |\n");
        this.printHand();

    }








}
