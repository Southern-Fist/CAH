package com.onegirloneguy.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String handle;
	private Integer playerPosition;
	private Hand playerHand = new Hand();
	private Integer score;
	
	public Player(){
		
		score = 0;
	}
	
	public Player(final String handle){
		
		this.handle = handle;
	}
	
	public String getHandle() {
		return handle;
	}
	public void setHandle(final String handle) {
		this.handle = handle;
	}
	
	public void addCard(final Card aCard) {
		playerHand.addCard(aCard);
	}
	public Card removeCard(final Integer position) {
		return playerHand.removeCard(position);
	}
	public List<Card> listHand() {
		return playerHand.listHand();
	}
	
	public void setHand(final ArrayList<Card> cards){
		
		playerHand.setHand(cards);
	}

	public Integer getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(final Integer playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	public Hand getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(final Hand playerHand) {
		this.playerHand = playerHand;
	}

	public void incrementScore(){
		
		score+=1;
	}
	
	public Integer getCurrentScore(){
		
		return score;
	}
	
	
}
