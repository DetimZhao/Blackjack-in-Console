package blackjack;

import java.util.Scanner;

/*
Inherits the class Person since Player is a person (Subclass of Person)
Player specific things in this class
 */
public class Player extends Person {

    // Using scanner to read in what the player types
    static Scanner input = new Scanner(System.in);

    // Constructor for Player
    public Player() {
        // super keyword refers to parent class Person, which calls the setName method
        super.setName("Player");
    }

    // variable choice (so I can confirm for hit and stand)
    private int choice = 0;

    // variable for the initial bet
    private double initialBet = 0.0;


    /*
        Since it extends the Person class, player already has a Hand
        But player does need access to the full deck and the pile of discards
         */
    public void makeNextDecision(Deck deck, Deck discards) {

        // Start of while loop (while not ending the program) to check what choice
        while(choice != -1) {
            displayChoices();
            choice = getChoice();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            // Hit choice
            if (choice == 1) {
                // Exit loop
                break;
            }
            // Stand choice
            else if (choice == 2) {
                System.out.println("\n| Player stands. |\n");
                // Need to exit out of loop
                break;
            }
            else if (choice == 9) {
                System.out.println("\n== Quitting Program ==\n");
                // Terminates the program
                System.exit(0);
            }
            else {
                System.out.println("\n!! Invalid, Try again. !!\n");
            }

        } // End of while loop

        // Hit choice
        if (choice == 1) {
            // Hits using deck and discards
            this.hit(deck, discards);
        }
        // Otherwise the choice is to stand
    }

    // Prints the options for the player
    public void displayChoices() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("What would you like to do?");
        System.out.println("1) Hit");
        System.out.println("2) Stand");
        System.out.println("9) Stop Playing");
    }

    // Prompts for and returns the scanner input of a number
    public static int getChoice() {
        System.out.print("Enter your choice: ");
        return input.nextInt();

    }

    // Returns true if choice was to stand, otherwise false
    public boolean stand() {
        return choice == 2;
    }

    // Prompts for and returns the bet number
    public double getBetAmount() {
        double betAmt = -1;
        boolean validBet = false;
        System.out.print("How much would you like to bet? ");
        betAmt = input.nextDouble();
        while (!validBet) {
            if (betAmt >= 2.0 && betAmt <= 500.0) {
                validBet = true;
            } else {
                System.out.print("You can't bet that amount. Try again. (Between $2 to $500) ");
                betAmt = input.nextDouble();
            }
        }
        return betAmt;
        }

        // Displays the starting money for the player
        public void displayStartMoney () {
            initialBet = getBetAmount();
            System.out.printf("\n{ Starting Bet Amount: $%.2f }\n\n", initialBet);
        }

        // Adds the bet to the starting bet, which will be updated in the blackjack class
        public double getInitialBet () {
            double startingBet = initialBet;
            return startingBet;
        }

        // Lets the player know they have no more money, prompts to continue or quit.
        public void hasNoMoneyInfo () {
            System.out.println("--> You're out of money... \n");
            System.out.println("Would you like to continue playing? ");
            System.out.println("1) Continue");
            System.out.println("9) Quit");
            while (choice != -1) {
                choice = getChoice();
                if (choice == 1) {
                    break;
                } else if (choice == 9) {
                    System.out.println("\n== Quitting Program ==\n");
                    // Terminates the program
                    System.exit(0);
                } else {
                    System.out.println("\n!! Invalid, Try again. !!\n");
                }
            }
            System.out.println("\n");
            displayStartMoney();
        }



}
