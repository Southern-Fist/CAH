package com.onegirloneguy.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Round {

	private final Map<String, Play> playedCards = new HashMap<String, Play>();
	private Player currentJudge;
	
	public void playHand(final Play play){
		
		playedCards.put(play.getPlayer(), play);
	}
	
	public Iterator<Play> showPlays(){
		
		return playedCards.values().iterator();
	}

	public Player getCurrentJudge() {
		return currentJudge;
	}

	public void setCurrentJudge(final Player currentJudge) {
		this.currentJudge = currentJudge;
	}
}
