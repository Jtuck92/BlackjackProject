package com.skilldistillery.blackjack.players;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.skilldistillery.common.cards.Deck;
import com.skilldistillery.common.cards.Hand;

public class Dealer {

	private Deck deck;
	List<Hand> players = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);

	public Dealer() {
			this.deck = new Deck();
			Hand player = new Hand("Player");
			Hand dealer = new Hand("Dealer");
			players.add(player);
			players.add(dealer);
		}

	public void startGame() {
		firstDeal();
	}

	public void firstDeal() {
		int choice = 0;
		if (deck.checkDeckSize() < 10) {
			this.deck = new Deck();
		}
		welcomeMenu();
		while (choice != 1 || choice != 2) {
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("That won't fly here, friend...");
				scanner.nextLine();
			}
			if (choice == 1) {
				for (Hand hand : players) {
					hand.resetHand();
					hand.addCard(deck);
					hand.addCard(deck);
				}
				displayTable();
				if (isRoundOver()) {
					firstDeal();
				} else {
					promptForAction(players.get(0), players.get(1));
				}
			}
			if (choice == 2) {
				System.out.println("You'll be back...");
				System.exit(0);
			} else if (choice > 2) {
				System.out.println("1 or 2, bud. That's what I said, ain't it?");
			}
		}
	}

	public void promptForAction(Hand p, Hand d) {
		System.out.println("Press 1 to Hit or 2 to Stay:");
		int selection = scanner.nextInt();
		scanner.nextLine();
		switch (selection) {
		case 1:
			p.addCard(deck);
			displayTable();
			if (isRoundOver()) {
				firstDeal();
			} else {
				promptForAction(p, d);
			}
			break;
		case 2:
			dealerLogic();
		default:
			break;
		}
	}

	public void dealerLogic() {
		while (players.get(1).getValue() < 17 && !(isRoundOver())) {
			System.out.println("Dealer drawing...");
			players.get(1).addCard(deck);
			displayTable();
		}
		if (players.get(0).getValue() == players.get(1).getValue()) {
			displayTable();
			System.out.println("Players tie: Push.");
			firstDeal();
		}
		if (isRoundOver()) {
			firstDeal();
		}
		if (players.get(0).getValue() > players.get(1).getValue() && players.get(1).getValue() < 22) {
			System.out.println("Player Wins.");
			if (isBlackjack(players.get(0))) {
			}
		} else {
			displayTable();
			System.out.println("Dealer Wins.");
		}
		firstDeal();
	}

	public boolean isRoundOver() {
		for (Hand hand : players) {
			if (isBlackjack(hand)) {
				System.out.println(hand.getName() + " has blackjack.");
				System.out.println(hand.getName() + " wins!");
				return true;
			} else if (isBust(hand)) {
				System.out.println(hand.getName() + " has busted.");
				System.out.println(hand.getName() + " loses.");
				return true;
			}
		}
		return false;
	}

	public boolean isBust(Hand hand) {
		return (hand.getValue() > 21) ? true : false;
	}

	public boolean isBlackjack(Hand hand) {
		return (hand.getValue() == 21) ? true : false;
	}

	public void displayTable() {
		System.out.println("_____________________________");
		System.out.println("Dealer's Hand: " + players.get(1).getHand());
		System.out.println();
		System.out.println("Player's Hand: " + players.get(0).getHand());
		System.out.println("_____________________________");
	}

	public void welcomeMenu() {
		System.out.println(" ____________________________________________");
		System.out.println("| Greetings, sucke- I mean... valued patron! |");
		System.out.println("|                                            |");
		System.out.println("|     1) Play hand of Blackjack              |");
		System.out.println("|     2) Shuffle away quietly                |");
		System.out.println("|____________________________________________|");
	}
}
