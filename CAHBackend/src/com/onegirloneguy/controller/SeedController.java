package com.onegirloneguy.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Hand;
import com.onegirloneguy.domain.Player;
import com.onegirloneguy.service.CardService;
import com.onegirloneguy.service.DealerService;
import com.onegirloneguy.service.impl.CardServiceImpl;


@Controller
@RequestMapping("seed")
public class SeedController {

	@Inject
	CardService service;
	
	@Inject
	DealerService dealerService;
	
	@RequestMapping("init/cards")
	@ResponseBody
	public void initCards(){
		
		dealerService.init();
		//service.seedCards();
	}
	
	
	@RequestMapping("init/cards/build/decks")
	@ResponseBody
	public void buildCards(){
		
		service.buildBlackDeck();
		service.buildWhiteDeck();
	}
	
	@RequestMapping("init/cards/shuffle")
	@ResponseBody
	public void shuffleCards(){
		
		dealerService.shuffleDecks();
	}
	
	@RequestMapping("draw/black/card")
	@ResponseBody
	public Card drawBlackCard(){
		
		return dealerService.drawCard(CardServiceImpl.BLACK_CARD);
	}
	
	@RequestMapping("draw/white/card")
	@ResponseBody
	public Card drawWhiteCard(){
		
		return dealerService.drawCard(CardServiceImpl.WHITE_CARD);
	}
	
	
	@RequestMapping("set/players")
	@ResponseBody
	public void seedPlayers(){
		
		final Player player1 = new Player("One");
		final Player player2 = new Player("Two");
		final Player player3 = new Player("Three");
		final Player player4 = new Player("Four");
		
		dealerService.registerPlayer(player1);
		dealerService.registerPlayer(player2);
		dealerService.registerPlayer(player3);
		dealerService.registerPlayer(player4);
	}
	
	@RequestMapping("deal/players")
	@ResponseBody
	public void dealPlayers(){
		
		dealerService.deal(13);
	}
	
	
	@RequestMapping("show/player/hand")
	@ResponseBody
	public List<Card> showPlayerHands(){
		
		return dealerService.getPlayer(0).listHand();
	}
	
	@RequestMapping("show/player/hand/id")
	@ResponseBody
	public List<Card> showPlayerHands(final Integer id){
		
		return dealerService.getPlayer(id).listHand();
	}
}
