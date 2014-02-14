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

	private final ArrayList<Player> players = new ArrayList<Player>();

	public DealerServiceImpl() {

	}

	public void init() {

		blackDeck = service.buildBlackDeck();
		whiteDeck = service.buildWhiteDeck();
		shuffleDecks();
	}

	@Override
	public void registerPlayer(final Player player) {

		players.add(player);
	}

	@Override
	public void removePlayer(final Player player) {

		players.remove(player);
	}

	public Player getPlayer(final Integer position) {

		return players.get(position.intValue());
	}

	@Override
	public void deal(final Integer numberOfCards) {

		for (int i = 0; i < numberOfCards; i++) {

			for (int j = 0; j < players.size(); j++) {

				final Player aPlayer = players.get(j);
				final Card aCard = drawCard(CardServiceImpl.WHITE_CARD);
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
	public Card drawCard(final Integer cardTypeId) {

		final Card card = drawCards(cardTypeId, 1).iterator().hasNext() ? drawCards(
				cardTypeId, 1).iterator().next() : null;

		return card;
	}

	@Override
	public Collection<Card> drawCards(final Integer cardTypeId, final Integer numberOfCards) {

		Collection<Card> cards = null;
		if (cardTypeId == CardServiceImpl.BLACK_CARD)
			cards = blackDeck.dealCards(numberOfCards);
		else
			cards = whiteDeck.dealCards(numberOfCards);

		return cards;
	}

}
