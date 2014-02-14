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
	
	private final Map<String, Game> gameMap = new HashMap<String, Game>();

	@Override
	public void createGame(final Game aGame) throws GameExistsException {

		if (!gameMap.containsKey(aGame.getGameName())){
			
			aGame.setBlackDeck(service.buildBlackDeck());
			aGame.setWhiteDeck(service.buildWhiteDeck());
			gameMap.put(aGame.getGameName(), aGame);
			logToConsole("Game " + aGame.getGameName() + " created!");
		}else{
			throw new GameExistsException("Game Already exists");
		}
	}
	
	private Game getGame(final String gameName){
		
		return gameMap.get(gameName);
	}

	@Override
	public void removeGame(final Game aGame) {

		gameMap.remove(aGame.getGameName());
	}

	@Override
	public void joinGame(final String gameName, final Player aPlayer) {
		
		final Game gme = gameMap.get(gameName);
		aPlayer.setHand(gme.dealHand());
		gme.addPlayer(aPlayer);
		logToConsole("Player " + aPlayer.getHandle() + " joined game " + gameName + "!");
	}

	@Override
	public void leaveGame(final Game aGame, final Player aPlayer) {

		gameMap.get(aGame.getGameName()).removePlayer(aPlayer);
	}

	@Override
	public void submitPlay(final PlayedHand ph) {

		final Game gme = gameMap.get(ph.getGameName());
		final Player plyr = gme.getPlayer(ph);
		final Iterator iter = (Iterator) ph.getCards().iterator();
		final Play play = new Play();
		
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
	public Iterator showPlays(final PlayedHand ph) {
		
		final Game gme = gameMap.get(ph.getGameName());
		return gme.showPlays();
	}

	private void logToConsole(final String msg){
		
		System.out.println("[DEBUG] - "+ msg);
	}

	@Override
	public Card drawBlackCard(final String gameName) {
		
		final Game gme = gameMap.get(gameName);
		final Card retval = gme.dealBlackCard();
		return retval;
	}

	@Override
	public List<Card> listPlayerHand(final String gameName, final String playerHandle) {
		
		final List<Card> retval = gameMap.get(gameName).getPlayer(playerHandle).listHand();
		return retval;
	}

	@Override
	public void selectWinner(final String gameName, final String playerHandle) {
		
		final Game gme = gameMap.get(gameName);
		final Player player = gme.getPlayer(playerHandle);
		player.incrementScore();
	}

	@Override
	public List<PlayerStateVO> getScoreList(final String gameName) {
		
		final Game gme = getGame(gameName);
		final Player[] players = gme.getPlayers();
		final List<PlayerStateVO> retval = new ArrayList<PlayerStateVO>();
		
		PlayerStateVO vo;
		for (int i = 0; i < gme.getCurrentPlayerCount(); i++){
			final Player aPlayer = players[i];
			vo = new PlayerStateVO();
			vo.populateVO(aPlayer);
			vo.setGameName(gameName);
			retval.add(vo);
		}
		return retval;
		
	}

	@Override
	public PlayerStateVO getCurrentPlayerState(final PlayerStateVO pvo) {

		final Game gme = getGame(pvo.getGameName());
		final Player p = gme.getPlayer(pvo.getPlayerHandle());
		
		final PlayerStateVO vo = new PlayerStateVO();
		vo.populateVO(p);
		vo.setGameName(pvo.getGameName());
		return vo;
	}

	@Override
	public void nextRound(final String gameName) {
		
		final Game gme = getGame(gameName);
		final Player judge = gme.getCurrentJudge();
		
		gme.newRound();
		
		Integer position = judge.getPlayerPosition();
		position = position + 1;
		position = position % gme.getCurrentPlayerCount();
		
		final Player ply = gme.getPlayer(position);
		gme.selectJudge(ply);
		
	}
	
}
