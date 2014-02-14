package com.onegirloneguy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.onegirloneguy.controller.vo.CardVO;
import com.onegirloneguy.controller.vo.PlayedHand;
import com.onegirloneguy.controller.vo.PlayerStateVO;
import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Game;
import com.onegirloneguy.domain.Play;
import com.onegirloneguy.domain.Player;
import com.onegirloneguy.service.CardService;
import com.onegirloneguy.service.GameService;
import com.oneguyonegirl.exception.GameExistsException;

@Service
public class GameServiceImpl implements GameService {

	@Inject
	CardService service;
	
	private Map<String, Game> gameMap = new HashMap<String, Game>();

	@Override
	public void createGame(Game aGame) throws GameExistsException {

		if (!gameMap.containsKey(aGame.getGameName())){
			
			aGame.setBlackDeck(service.buildBlackDeck());
			aGame.setWhiteDeck(service.buildWhiteDeck());
			gameMap.put(aGame.getGameName(), aGame);
			logToConsole("Game " + aGame.getGameName() + " created!");
		}else{
			throw new GameExistsException("Game Already exists");
		}
	}
	
	private Game getGame(String gameName){
		
		return gameMap.get(gameName);
	}

	@Override
	public void removeGame(Game aGame) {

		gameMap.remove(aGame.getGameName());
	}

	@Override
	public void joinGame(String gameName, Player aPlayer) {
		
		Game gme = gameMap.get(gameName);
		aPlayer.setHand(gme.dealHand());
		gme.addPlayer(aPlayer);
		logToConsole("Player " + aPlayer.getHandle() + " joined game " + gameName + "!");
	}

	@Override
	public void leaveGame(Game aGame, Player aPlayer) {

		gameMap.get(aGame.getGameName()).removePlayer(aPlayer);
	}

	@Override
	public void submitPlay(PlayedHand ph) {

		Game gme = gameMap.get(ph.getGameName());
		Player plyr = gme.getPlayer(ph);
		Iterator iter = (Iterator) ph.getCards().iterator();
		Play play = new Play();
		
		play.setPlayer(ph.getPlayerHandle());
		
		CardVO vo = new CardVO();
		vo.populate(plyr.removeCard((Integer)iter.next()));
		
		play.setFirstCard(vo);
		if (iter.hasNext()){
			vo = new CardVO();
			vo.populate(plyr.removeCard((Integer)iter.next()));
			play.setSecondCard(vo);
		}
		gme.play(play);
		logToConsole("Play submitted - Card 1 " + play.getFirstCard().getCardId());
	}

	@Override
	public Iterator showPlays(PlayedHand ph) {
		
		Game gme = gameMap.get(ph.getGameName());
		return gme.showPlays();
	}

	private void logToConsole(String msg){
		
		System.out.println("[DEBUG] - "+ msg);
	}

	@Override
	public Card drawBlackCard(String gameName) {
		
		Game gme = gameMap.get(gameName);
		Card retval = gme.dealBlackCard();
		return retval;
	}

	@Override
	public List<Card> listPlayerHand(String gameName, String playerHandle) {
		
		List<Card> retval = gameMap.get(gameName).getPlayer(playerHandle).listHand();
		return retval;
	}

	@Override
	public void selectWinner(String gameName, String playerHandle) {
		
		Game gme = gameMap.get(gameName);
		Player player = gme.getPlayer(playerHandle);
		player.incrementScore();
	}

	@Override
	public List<PlayerStateVO> getScoreList(String gameName) {
		
		Game gme = getGame(gameName);
		Player[] players = gme.getPlayers();
		List<PlayerStateVO> retval = new ArrayList<PlayerStateVO>();
		
		PlayerStateVO vo;
		for (int i = 0; i < gme.getCurrentPlayerCount(); i++){
			Player aPlayer = players[i];
			vo = new PlayerStateVO();
			vo.populateVO(aPlayer);
			vo.setGameName(gameName);
			retval.add(vo);
		}
		return retval;
		
	}

	@Override
	public PlayerStateVO getCurrentPlayerState(PlayerStateVO pvo) {

		Game gme = getGame(pvo.getGameName());
		Player p = gme.getPlayer(pvo.getPlayerHandle());
		
		PlayerStateVO vo = new PlayerStateVO();
		vo.populateVO(p);
		vo.setGameName(pvo.getGameName());
		return vo;
	}

	@Override
	public void nextRound(String gameName) {
		
		Game gme = getGame(gameName);
		Player judge = gme.getCurrentJudge();
		
		gme.newRound();
		
		Integer position = judge.getPlayerPosition();
		position = position + 1;
		position = position % gme.getCurrentPlayerCount();
		
		Player ply = gme.getPlayer(position);
		gme.selectJudge(ply);
		
	}
	
}
