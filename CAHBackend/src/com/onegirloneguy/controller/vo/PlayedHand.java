package com.onegirloneguy.controller.vo;

import java.util.ArrayList;

import com.onegirloneguy.domain.Card;

public class PlayedHand {

	private String gameName;
	private String playerHandle;
	private Integer playerPosition;
	private ArrayList<Integer> cards;
	
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(final String gameName) {
		this.gameName = gameName;
	}
	public String getPlayerHandle() {
		return playerHandle;
	}
	public void setPlayerHandle(final String playerHandle) {
		this.playerHandle = playerHandle;
	}
	public Integer getPlayerPosition() {
		return playerPosition;
	}
	public void setPlayerPosition(final Integer playerPosition) {
		this.playerPosition = playerPosition;
	}
	public ArrayList<Integer> getCards() {
		return cards;
	}
	public void setCards(final ArrayList<Integer> cards) {
		this.cards = cards;
	}
	
	
}
