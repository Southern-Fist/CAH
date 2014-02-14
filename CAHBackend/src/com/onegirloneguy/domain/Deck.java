package com.onegirloneguy.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Deck {

	private List<Card> cards;
	private Iterator<Card> sequentialDeck;
	private String deckType;

	public Deck() {

	}

	public Deck(ArrayList<Card> cards, String deckType) {

		this.cards = cards;
		this.deckType = deckType;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public String getDeckType() {
		return deckType;
	}

	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}

	public void shuffleDeck() {

		int totalCards = cards.size();
		Card[] deck = (Card[]) cards.toArray(new Card[totalCards]);
		Card[] shuffledDeck = new Card[totalCards];
		
		System.out.println("[DEBUG] Total Cards: " + totalCards);
		System.out.println("[DEBUG] Card Colour: " + this.deckType);
		
		for (int i = 0; i < totalCards; i++){
			int newPos; 
			//get a card from the deck
			Card card = deck[i];
			//get a new random position that is guaranteed to never hit that last position.
			newPos = randomize(0, totalCards-2);
			
			//Iteration Count
			System.out.println("[DEBUG] Outer Iteration: " + i);
			
			System.out.println("[DEBUG] Random Position: " + newPos);
			
			//If we get to the end of the array, go to the front and find the next available position.  
			//I will change this to use recurrsion and be more performant later.
			//if (newPos >= totalCards-1)
			//	newPos = 0;
			//Check to see if we already put something in this position
			while (shuffledDeck[newPos] != null){
				//if we already got this position, inc by 1 and put it there.  not enitrely random, but I don't want to 
				//get to a state where I am constantly trying to find a new spot.  Could make this better later.
				System.out.println("[DEBUG] incremented Position: " + newPos);
				
				//If we get to the end of the array, go to the front and find the next available position.  
				//I will change this to use recurrsion and be more performant later.
				if (newPos >= totalCards-1)
					newPos = 0;
				else 
					newPos +=1;
				
			}
			//add to the new deck
			System.out.println("[DEBUG] added Position: " + newPos);
			shuffledDeck[newPos] = card;	
		}
		
		//cards = (ArrayList<CardDeck>) Arrays.asList(shuffledDeck);
		 
		cards = Arrays.asList(shuffledDeck);
		System.out.println("[DEBUG] Number of Cards: " + cards.size());
		sequentialDeck = cards.iterator();
	}

	public Card dealCard(){
		
		//return cards.remove(0);
		return sequentialDeck.next();
	}
	
	public ArrayList<Card> dealCards(int numberOfCardsToDeal){
		
		ArrayList<Card> list = new ArrayList<Card>();
		for (int i = 0; i < numberOfCardsToDeal; i++){
			
			
			list.add(sequentialDeck.next());
		}
		return list;
	}
	
	private int randomize(int min, int max) {

		int range = Math.abs(max - min) + 1;
		return (int) (Math.random() * range) + (min <= max ? min : max);

	}
	
	public Integer size(){
		
		return cards.size();
	}

}
