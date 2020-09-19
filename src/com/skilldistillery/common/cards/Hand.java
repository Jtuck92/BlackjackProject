package com.skilldistillery.common.cards;

	import java.util.ArrayList;
	import java.util.List;
	
	public class Hand {
		private String name;
		List<Card> hand = new ArrayList<>();
		public Hand(String name) {
			this.name = name;
		}
		public void addCard(Deck d) {
			hand.add(d.dealCard());
		}
		public List<Card> getHand() {
			return hand;
		}
		public void setHand(List<Card> hand) {
			this.hand = hand;
		}
		public int getValue() {
			int value = 0;
			for (Card card : this.hand) {
				value += card.getValue();
			}
			return value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void resetHand() {
			for (int i = 0; i < hand.size(); i++) {
				hand.clear();
			}
		}
		@Override
		public String toString() {
			return "Hand = " + hand;
		}
	}

