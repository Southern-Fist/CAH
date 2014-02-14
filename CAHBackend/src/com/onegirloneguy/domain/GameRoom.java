package com.onegirloneguy.domain;

import java.util.ArrayList;

public class GameRoom {

	private static final Integer DEFAULT_MAX_PLAYERS = 6;
	private static final Integer DEFAULT_MAX_SPECTATORS = 6;
	private static final Integer DEFAULT_MAX_IDLE_TIMER = 60; //In seconds
	
	private String roomName;
	private final Integer maxNumberOfPlayers;
	private final Integer idleTimer;
	private final Integer maxNumberOfSpectators;
	private final ArrayList<Player> players;
	
	
	
	public GameRoom(){
		
		maxNumberOfPlayers = DEFAULT_MAX_PLAYERS;
		maxNumberOfSpectators = DEFAULT_MAX_SPECTATORS;
		idleTimer = DEFAULT_MAX_IDLE_TIMER;
		players = new ArrayList<Player>();
	}
	
	public void addPlayer(final Player player){
		
		players.add(player);
	}
	
	public void removePlayer(final Player player){
		
		players.remove(player);
	}
	
}
