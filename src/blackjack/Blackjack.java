package blackjack;

/*
Implementing most of the blackjack's rules and other necessary logic
 */
public class Blackjack {

    private int wins;
    private int losses;
    private int pushes; // Term that means ties between the dealer and player

    private final Deck deck;
    private final Deck discards; // This means all cards that have already been dealt

    private final Dealer dealer;
    private final Player player;

    private double startAmount = 0.0;
    private double totalMoney = 0.0;

    private static int oldWins = 0;
    private static int oldLosses = 0;
    private static int oldPushes = 0;



    // Constructor that makes the necessary variables to start a game of Blackjack
    public Blackjack() {

        // Need People to play/start game
        this.dealer = new Dealer();
        this.player = new Player();

        // Create a new full deck (52 cards)
        this.deck = new Deck();
        deck.createDeckOfCards();

        // Make a new deck for used cards
        this.discards = new Deck();

        // Shuffles the deck and starts the game
        deck.shuffleDeck();

    }

    /*
    Starts a new round for the game
    Displays stats
    Gives out cards
    Checks for blackjack
    Gives the player the choice to do stuff
     */
    public void gameStartRound() {

        // Infinite loop to keep game going
        while(true) {
            boolean roundOver = false; // variable to indicate when round is over (resets once true)
            while (!roundOver) {
                // If the player's has 0 money is true, then it should prompt for a bet
                if (player.getInitialBet() == 0.0) {
                    player.displayStartMoney();
                }

                // Displays stats after a win, loss, or push
                if (wins > 0 || losses > 0 || pushes > 0) {
                    System.out.print("=====================================");
                    System.out.printf("\n\t\t[Starting new round]\n | Wins: %d | Losses: %d | Pushes: %d |\n", wins, losses, pushes);

                    /*
                    Displays the money for the player
                    Checks whether it is a win, lost, or push since it's all in this big if statement that updates stats
                    The check is done by comparing the current value to the old value of wins/losses/pushes
                     */
                    if (wins != oldWins) {
                        oldWins = wins; // Sets old number to new for next comparison
                        this.startAmount = player.getInitialBet();
                        if (player.hasBlackjack()) {
                            // Player wins 1.5 of starting bet if blackjack is true
                            totalMoney += (startAmount * 1.50 + startAmount);
                        }
                        else {
                            // Player wins their initial bet (in addition to their starting bet)
                            totalMoney += startAmount + startAmount;
                        }
                        System.out.printf("\t\tTotal money: $%.2f\n", totalMoney);
                    }
                    else if (losses != oldLosses) {
                        oldLosses = losses; // Sets old number to new for next comparison
                        this.startAmount = player.getInitialBet();
                        // Player loses initial bet
                        totalMoney -= startAmount;
                        System.out.printf("\t\tTotal money: $%.2f\n", totalMoney);
                        // Checks if out of money, then prompt for more
                        if (totalMoney <= 0.0) {
                            System.out.println("=====================================\n");
                            System.out.println("=====================================\n");
                            player.hasNoMoneyInfo();
                        }
                    } else if (pushes != oldPushes) {
                        oldPushes = pushes; // Sets old number to new for next comparison
                        // Money won't change
                        System.out.printf("\t\tTotal money: $%.2f\n", totalMoney);
                    }

                    System.out.println("=====================================\n"); // Prints bar to make it more visually appealing

                    // Puts their cards back into the deck
                    dealer.getHand().discardHandToDeck(discards);
                    player.getHand().discardHandToDeck(discards);
                }


                // Check to see the deck has at least 4 cards left for (2 for each)
                if (deck.cardsLeft() < 4) {
                    // If true then make deck from discards
                    deck.createDeckFromDiscards(discards);
                }

                // Need to give the Dealer two cards to start
                dealer.getHand().takeCardFromDeck(deck);
                dealer.getHand().takeCardFromDeck(deck);

                //Need to give the Player two cards to start
                player.getHand().takeCardFromDeck(deck);
                player.getHand().takeCardFromDeck(deck);

                // Print out their cards
                dealer.printStartHand();
                player.printHand();

                // If dealer has blackjack in the start
                if (dealer.hasBlackjack()) {
                    // Display the dealer's cards
                    dealer.printHand();

                    // Then check if player has blackjack
                    if (player.hasBlackjack()) {
                        // Round is over, it is a tie/push
                        System.out.println("--> Both you and the Dealer have (natural) Blackjack! It is a push.\n");
                        // Increment pushes/ties
                        pushes++;
                        // Round is over, so it exits and starts another
                        roundOver = true;
                    }
                    // Otherwise, the dealer wins
                    else {
                        System.out.println("--> The Dealer has (natural) Blackjack! Sorry, you lose.\n");
                        // Increment losses
                        losses++;
                        // Round is over, so it exits and starts another
                        roundOver = true;
                    }
                }

                /*
                If the previous is false, then dealer doesn't have blackjack
                Check if player has blackjack in the start
                 */
                if (player.hasBlackjack()) {
                    System.out.println("--> You have (natural) Blackjack! Congrats, you win!\n");
                    // Increment wins
                    wins++;
                    // Round is over, so it exits and starts another
                    roundOver = true;
                }


                /*
                Lets the player make a decision when their hand value is less than 21
                If they hit, and it is still under 21, the player gets to make another decision.
                again to get closer to 21 or stand
                 */
                while (player.getHand().calculateHandValue() < 21 && !roundOver) {
                    player.makeNextDecision(deck, discards);
                    // Check if player busts
                    if (player.getHand().calculateHandValue() > 21) {
                        System.out.println("--> You have busted.\n");
                        // Increment losses
                        losses++;
                        // Round is over, so it exits and starts another
                        roundOver = true;
                    }
                    // if the player chose to stand
                    if (player.stand()) {
                        // exit this loop
                        break;
                    }
                }

                if(!roundOver) {
                    // Dealer's turn
                    System.out.println("< Dealer's Turn >\n\n");
                    dealer.printHand();
                    // Dealer must (hit) take a card if he is at 16 or below, otherwise stand at 17 or above
                    while (dealer.getHand().calculateHandValue() < 17) {
                        dealer.hit(deck, discards);
                    }

                    // After dealer's turn, need to determine the winner
                    determineWinner();
                }
            }
        }
    }

    /*
    Determines the winner of the round by comparing
    the player's and dealer's hand value
     */
    public void determineWinner() {
        int dealerHandValue = dealer.getHand().calculateHandValue();
        int playerHandValue = player.getHand().calculateHandValue();
        // if tree to check values between the dealer and player, which figures out who wins/loses.
        if (dealerHandValue > 21) {
            System.out.println("--> Dealer busts. You win. \n");
            wins++;
        } else if (dealerHandValue > playerHandValue && playerHandValue < 21) {
            System.out.println("--> You lose.\n");
            losses++;
        } else if (playerHandValue > dealerHandValue) {
            System.out.println("--> You win.\n");
            wins++;
        } else {
            System.out.println("--> It is a push.\n");
            pushes++;
        }
    }

}

