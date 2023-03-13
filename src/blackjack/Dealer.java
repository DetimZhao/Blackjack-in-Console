package blackjack;

/*
Inherits the class Person since Dealer is a person (Subclass of Person)
Dealer specific things in this class
 */
public class Dealer extends Person {

    // Constructor for Dealer
    public Dealer() {
        // super keyword refers to parent class Person, which calls the setName method
        super.setName("Dealer");
    }

    // Part of blackjack rules, where the dealer has one card face down while the other card is shown
    public void printStartHand() {
        System.out.println("Dealer's hand: ");
        System.out.println("----------------------");
        System.out.print(super.getHand().getFirstCard(0));
        // If there is an ace card as the first card
        if(super.getHand().getFirstCard(0).getValue() == 1) {
            System.out.print(" or 11"); // Just letting the player know the ace can be 1 or 11
        }
        System.out.println("\nSecond card is face down. - ?\n");
    }




}
