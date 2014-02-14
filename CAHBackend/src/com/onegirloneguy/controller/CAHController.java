package com.onegirloneguy.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onegirloneguy.controller.vo.CardVO;
import com.onegirloneguy.controller.vo.PlayedHand;
import com.onegirloneguy.controller.vo.PlayerStateVO;
import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Game;
import com.onegirloneguy.domain.Player;
import com.onegirloneguy.service.GameService;
import com.oneguyonegirl.exception.GameExistsException;

@Controller
@RequestMapping("CAH")
public class CAHController {

	
	@Inject
	private GameService gameService;
	
	@RequestMapping("game/create")
	@ResponseBody
	public void createGame(){
		
		final Game gme = new Game();
		gme.setGameName("Game-On");
		
		try{
		
			gameService.createGame(gme);
		}catch (final GameExistsException ge){
			
			ge.printStackTrace();
		}
		
	}
	
	@RequestMapping("game/create{vo}")
	@ResponseBody
	public void createGame(final PlayerStateVO vo){
		
		System.out.println("param: " + vo);
		final Game gme = new Game();
		gme.setGameName(vo.getGameName());
		
		try{
		
			gameService.createGame(gme);
		}catch (final GameExistsException ge){
			
			ge.printStackTrace();
		}
		
	}
	
	@RequestMapping("game/join")
	@ResponseBody
	public void joinGame(){
		
		Player aPlayer = new Player();
		aPlayer.setHandle("Mikey");
		
		gameService.joinGame("Game-On", aPlayer);
		
		aPlayer = new Player();
		aPlayer.setHandle("Mikey 2");
		
		gameService.joinGame("Game-On", aPlayer);
		
		aPlayer = new Player();
		aPlayer.setHandle("Mikey 3");
		
		gameService.joinGame("Game-On", aPlayer);
		
		aPlayer = new Player();
		aPlayer.setHandle("Mikey 4");
		
		gameService.joinGame("Game-On", aPlayer);
		
	}
	
	
	@RequestMapping("game/playhand")
	@ResponseBody
	public void submitHand(){
		
		
		
		PlayedHand ph = new PlayedHand();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(5);
		ph.setGameName("Game-On");
		ph.setPlayerHandle("Mikey");
		ph.setPlayerPosition(1);
		ph.setCards(list);
		
		gameService.submitPlay(ph);
		
		ph = new PlayedHand();
		list = new ArrayList<Integer>();
		
		list.add(3);
		list.add(5);
		ph.setGameName("Game-On");
		ph.setPlayerHandle("Mikey 2");
		ph.setPlayerPosition(2);
		ph.setCards(list);
		
		gameService.submitPlay(ph);
		
		ph = new PlayedHand();
		list = new ArrayList<Integer>();
		
		list.add(3);
		list.add(5);
		ph.setGameName("Game-On");
		ph.setPlayerHandle("Mikey 3");
		ph.setPlayerPosition(3);
		ph.setCards(list);
		
		gameService.submitPlay(ph);
		
		ph = new PlayedHand();
		list = new ArrayList<Integer>();
		
		list.add(3);
		list.add(5);
		ph.setGameName("Game-On");
		ph.setPlayerHandle("Mikey 4");
		ph.setPlayerPosition(4);
		ph.setCards(list);
		
		gameService.submitPlay(ph);
	}
	
	@RequestMapping("game/showplays")
	@ResponseBody
	public Iterator showPlays(){
		
		final PlayedHand ph = new PlayedHand();
		ph.setGameName("Game-On");
		return gameService.showPlays(ph);
	}
	
	@RequestMapping("game/draw/blackcard")
	@ResponseBody
	public CardVO drawBlackCard(){
		
		final CardVO retval = new CardVO();
		retval.populate(gameService.drawBlackCard("Game-On"));
		return retval;
	}
	
	@RequestMapping("game/list/playerhand")
	@ResponseBody
	public List<Card> listHand(){
		
		final List<Card> retval = gameService.listPlayerHand("Game-On", "Mikey");
		return retval;
	}
	
	@RequestMapping("game/select/winner")
	@ResponseBody
	public void selectWinner(final String handle, final String gameName){
		
		gameService.selectWinner(gameName, handle);
	}
	
	@RequestMapping("game/show/scores")
	@ResponseBody
	public List<PlayerStateVO> showScores(){
		
		final List<PlayerStateVO> retval = gameService.getScoreList("Game-On");  
		return retval;
	}
	
	
	@RequestMapping(value="game/get/player/state", method=RequestMethod.POST)
	@ResponseBody
	public PlayerStateVO getPlayerState(final PlayerStateVO vo){
		
		System.out.println("Value: " + vo.getGameName()+ " " + vo.toString());
		final PlayerStateVO retval = gameService.getCurrentPlayerState(vo);
		return retval;
	}
}
