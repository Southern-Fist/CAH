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
	
	public Player(String handle){
		
		this.handle = handle;
	}
	
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	public void addCard(Card aCard) {
		playerHand.addCard(aCard);
	}
	public Card removeCard(Integer position) {
		return playerHand.removeCard(position);
	}
	public List<Card> listHand() {
		return playerHand.listHand();
	}
	
	public void setHand(ArrayList<Card> cards){
		
		playerHand.setHand(cards);
	}

	public Integer getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(Integer playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	public Hand getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}

	public void incrementScore(){
		
		score+=1;
	}
	
	public Integer getCurrentScore(){
		
		return score;
	}
	
	
}
