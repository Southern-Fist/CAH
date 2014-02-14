package com.onegirloneguy.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.onegirloneguy.controller.vo.PlayedHand;

public class Game {

	private Deck whiteDeck;
	private Deck blackDeck;

	private String gameName;
	private Integer maxPlayers;
	private Player[] players;
	private Integer currentNoPlayers;
	private Round aRound;

	public Game() {

		maxPlayers = 6;
		currentNoPlayers = 0;
		players = new Player[maxPlayers];
		aRound = new Round();
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(final String gameName) {
		this.gameName = gameName;
	}

	public Deck getWhiteDeck() {
		return whiteDeck;
	}

	public void setWhiteDeck(final Deck whiteDeck) {
		whiteDeck.shuffleDeck();
		this.whiteDeck = whiteDeck;
	}

	public Deck getBlackDeck() {
		return blackDeck;
	}

	public void setBlackDeck(final Deck blackDeck) {
		blackDeck.shuffleDeck();
		this.blackDeck = blackDeck;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(final Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Player getPlayer(final PlayedHand ph) {

		Player retval = players[ph.getPlayerPosition()];
		if (!ph.getPlayerHandle().equalsIgnoreCase(retval.getHandle())) {
			retval = getPlayer(ph.getPlayerHandle());
		}
		return retval;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(final Player[] players) {
		this.players = players;
	}

	// Use this method when the positions have changed and the ph object handle
	// doesn't match the position
	public Player getPlayer(final String handle) {

		Player retval = null;
		for (int i = 0; i < getCurrentPlayerCount(); i++) {
			retval = players[i];
			if (handle.equalsIgnoreCase(retval.getHandle()))
				break;
		}
		return retval;
	}
	
	public Player getPlayer(final Integer pos){
		
		final Player retval = players[pos]; 
		return retval;
	}

	public void addPlayer(final Player aPlayer) {

		if (getCurrentPlayerCount() == maxPlayers)
			return;

		increasePlayerCount();
		aPlayer.setPlayerPosition(getCurrentPlayerCount());
		players[getCurrentPlayerCount().intValue() - 1] = aPlayer;
	}

	public void removePlayer(final Player aPlayer) {

		decreasePlayerCount();
		players[aPlayer.getPlayerPosition() - 1] = null;
		reindexPlayerPositions();
	}

	public Integer getCurrentPlayerCount() {

		return currentNoPlayers;
	}

	private void increasePlayerCount() {

		currentNoPlayers += 1;
	}

	private void decreasePlayerCount() {

		currentNoPlayers -= 1;
	}

	private void reindexPlayerPositions() {

		for (int i = 0; i < players.length; i++) {

			final Player player = players[i];
			if (player == null) {
				shuffleForward(i);
			}
		}
	}

	private void shuffleForward(final int pos) {

		final int len = getCurrentPlayerCount();

		for (int i = pos; i < len; i++) {

			players[i] = players[i + 1];
			players[i].setPlayerPosition(i + 1);
		}
	}

	public void play(final Play play) {

		aRound.playHand(play);
	}

	public Iterator<Play> showPlays() {

		final Iterator<Play> retval = aRound.showPlays();
		System.out.println("[DEBUG] : " + retval + " " + retval.hasNext());
		return retval;
	}

	public void selectJudge(final Player player) {

		aRound.setCurrentJudge(player);
	}

	public Player getCurrentJudge() {

		return aRound.getCurrentJudge();
	}

	public void newRound() {

		aRound = new Round();
	}

	public ArrayList<Card> dealHand() {

		final ArrayList<Card> retval = new ArrayList<Card>();
		for (int i = 0; i < 13; i++)
			retval.add(whiteDeck.dealCard());

		return retval;
	}

	public Card dealBlackCard() {

		final Card retval = blackDeck.dealCard();
		return retval;
	}

	public List<Card> listPlayerHand(final String playerHandle) {

		final List<Card> retval = getPlayer(playerHandle).listHand();
		return retval;
	}

}
