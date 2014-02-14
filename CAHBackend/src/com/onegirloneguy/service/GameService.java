package com.onegirloneguy.service;

import java.util.Iterator;
import java.util.List;

import com.onegirloneguy.controller.vo.PlayedHand;
import com.onegirloneguy.controller.vo.PlayerStateVO;
import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Game;
import com.onegirloneguy.domain.Play;
import com.onegirloneguy.domain.Player;
import com.oneguyonegirl.exception.GameExistsException;

/*once this is prototyped, switch to using game room id's.  Will do this in the lobby service*/

public interface GameService {

	public void createGame(Game aGame) throws GameExistsException;
	public void removeGame(Game aGame);
	public void joinGame(String gameName, Player aPlayer);
	public void leaveGame(Game aGame, Player aPlayer);
	public void submitPlay(PlayedHand ph);
	public Iterator<Play> showPlays(PlayedHand ph);
	public Card drawBlackCard(String gameName);
	public List<Card> listPlayerHand(String gameName, String playerHandle);
	public void selectWinner(String gameName, String playerHandle);
	public List<PlayerStateVO> getScoreList(String gameName);
	public PlayerStateVO getCurrentPlayerState(PlayerStateVO vo);
	public void nextRound(String gameName);
}
