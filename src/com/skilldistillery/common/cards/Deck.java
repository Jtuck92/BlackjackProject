package com.skilldistillery.common.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> deck = new ArrayList<Card>(52);

	public Deck() {
		this.deck = createDeck();
	}

	public List<Card> createDeck() {
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck.add(new Card(s, r));
			}
		}
		shuffleDeck();
		return deck;
	}

	public int checkDeckSize() {
		return deck.size();
	}

	public Card dealCard() {
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}

	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
	}
}
