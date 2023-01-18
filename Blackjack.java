package Blackjack;

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //Welcome message
        System.out.println("Welcome to BlackJack!");

        //Create playing deck
        Deck playingDeck = new Deck();
        playingDeck.getFullDeck();
        playingDeck.shuffle();

        //Create player hand / stats
        Deck playerHand = new Deck();
        double playerMoney = 100.00;

        //Create dealer hand
        Deck dealerDeck = new Deck();

        //Game Loop
        while (playerMoney > 0) {
            System.out.println("You have $" + playerMoney + "\nHow much will you bet?");
            double playerBet = scan.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("Throw them out!");
                break;
            }
            boolean endRound = false;

            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while (true) {
                System.out.println("Your hand: ");
                System.out.println(playerHand.toString());
                System.out.println("Your deck value is: " + playerHand.cardsValue());

                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + " and [HIDDEN]");

                System.out.println("Hit(1) or Stand(2)?");
                int response = scan.nextInt();

                //Hit
                if (response == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You drew: " + playerHand.getCard(playerHand.deckSize() - 1).toString());

                    //Over 21
                    if (playerHand.cardsValue() > 21) {
                        System.out.println("Bust! Cards equal: " + playerHand.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                //Stand
                if (response == 2) {
                    break;
                }
            }

            //Reveal dealer cards
            System.out.println("Dealer cards: " + dealerDeck.toString());
            if (dealerDeck.cardsValue() > playerHand.cardsValue() && endRound == false) {
                System.out.println("Dealer wins");
                playerMoney -= playerBet;
                endRound = true;
            }

            while(dealerDeck.cardsValue() < 17 && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer drew: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }

            System.out.println("Dealer's hand value is: " + dealerDeck.cardsValue());
            if (dealerDeck.cardsValue() > 21 && endRound == false) {
                System.out.println("Dealer busts! You win!");
                playerMoney += playerBet;
                endRound = true;
            }

            if (dealerDeck.cardsValue() == playerHand.cardsValue() && endRound == false) {
                System.out.println("Push.");
                endRound = true;
            }

            if (playerHand.cardsValue() > dealerDeck.cardsValue() && endRound == false) {
                System.out.println("You win!");
                playerMoney += playerBet;
                endRound = true;
            } 
            
            else if (endRound == false) {
                System.out.println("You lost the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerHand.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of hand.");
        }

        System.out.println("Game Over!");
    }
}