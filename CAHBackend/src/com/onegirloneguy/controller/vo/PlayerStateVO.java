package com.onegirloneguy.controller.vo;

import com.onegirloneguy.domain.Player;

public class PlayerStateVO {

	private String gameName;
	private String playerHandle;
	private PlayedHand hand;
	private Integer position;
	private Integer score;
	private Boolean bolJudge;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getPlayerHandle() {
		return playerHandle;
	}

	public void setPlayerHandle(String playerHandle) {
		this.playerHandle = playerHandle;
	}

	public PlayedHand getHand() {
		return hand;
	}

	public void setHand(PlayedHand hand) {
		this.hand = hand;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void populateVO(Player p) {

		if (this.getPlayerHandle() == null)
			this.setPlayerHandle(p.getHandle());
		this.setPosition(p.getPlayerPosition());
		this.setScore(p.getCurrentScore());
	}
	
	public String toString(){
		
		String retval = getGameName() + " : " + getPlayerHandle() + " : " + getPosition();
		return retval;
	}

}
