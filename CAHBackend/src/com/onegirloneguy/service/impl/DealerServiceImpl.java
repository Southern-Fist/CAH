package com.onegirloneguy.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Deck;
import com.onegirloneguy.domain.Player;
import com.onegirloneguy.service.CardService;
import com.onegirloneguy.service.DealerService;

@Service
public class DealerServiceImpl implements DealerService {

	@Inject
	CardService service;

	private Deck whiteDeck;
	private Deck blackDeck;

	private ArrayList<Player> players = new ArrayList<Player>();

	public DealerServiceImpl() {

	}

	public void init() {

		blackDeck = service.buildBlackDeck();
		whiteDeck = service.buildWhiteDeck();
		shuffleDecks();
	}

	@Override
	public void registerPlayer(Player player) {

		players.add(player);
	}

	@Override
	public void removePlayer(Player player) {

		players.remove(player);
	}

	public Player getPlayer(Integer position) {

		return players.get(position.intValue());
	}

	@Override
	public void deal(Integer numberOfCards) {

		for (int i = 0; i < numberOfCards; i++) {

			for (int j = 0; j < players.size(); j++) {

				Player aPlayer = players.get(j);
				Card aCard = drawCard(CardServiceImpl.WHITE_CARD);
				aPlayer.addCard(aCard);
			}
		}
	}

	@Override
	public Card drawBlackCard() {

		return drawCard(CardServiceImpl.BLACK_CARD);
	}

	public void shuffleDecks() {

		whiteDeck.shuffleDeck();
		blackDeck.shuffleDeck();
	}

	@Override
	public Card drawCard(Integer cardTypeId) {

		Card card = drawCards(cardTypeId, 1).iterator().hasNext() ? drawCards(
				cardTypeId, 1).iterator().next() : null;

		return card;
	}

	@Override
	public Collection<Card> drawCards(Integer cardTypeId, Integer numberOfCards) {

		Collection<Card> cards = null;
		if (cardTypeId == CardServiceImpl.BLACK_CARD)
			cards = blackDeck.dealCards(numberOfCards);
		else
			cards = whiteDeck.dealCards(numberOfCards);

		return cards;
	}

}
