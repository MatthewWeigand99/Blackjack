package Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void getFullDeck(){
        for (Suit cardSuit : Suit.values()) {
            for (Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>();
        Random rand = new Random();

        int randCardIndex = 0;
        int originalSize = this.cards.size();

        for (int i = 0; i < originalSize; i++) {
            randCardIndex = rand.nextInt((this.cards.size()-1 - 0) + 1) + 0;
            temp.add(this.cards.get(randCardIndex));
            this.cards.remove(randCardIndex);
        }

        this.cards = temp;
    }

    public String toString() {
        String cardListOutput = "";

        for (Card aCard : this.cards) {
            cardListOutput += "\n-" + aCard.toString();
        }

        return cardListOutput;
    }

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    public void draw(Deck comingFromDeck) {
        this.cards.add(comingFromDeck.getCard(0));
        comingFromDeck.removeCard(0);
    }

    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card aCard : this.cards) {
            switch (aCard.getValue()) {
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }

        for (int i = 0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }

        return totalValue;
    }

    public int deckSize() {
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.cards.size();

        for (int i = 0; i < this.deckSize(); i++) {
            moveTo.addCard(this.getCard(i));
        } 

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }
}
