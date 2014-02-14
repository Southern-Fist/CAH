package com.onegirloneguy.service.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.onegirloneguy.dao.CardDAO;
import com.onegirloneguy.domain.Card;
import com.onegirloneguy.domain.Deck;
import com.onegirloneguy.service.CardService;
import com.oneguyonegirl.util.CAHUtil;

@Service
public class CardServiceImpl implements CardService {

	@Inject
	CardDAO dao;

	public static final Integer BLACK_CARD = 1;
	public static final Integer WHITE_CARD = 2;

	// private Deck whiteDeck;
	// private Deck blackDeck;

	@Override
	public void seedCards() {

		try {
			System.out.println("Testing!!!");
			String[] cardArray = null;
			/*
			 * = CAHUtil .loadCards(
			 * "/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards.txt"
			 * ); saveCards(cardArray, 1);
			 */

			cardArray = CAHUtil.getInstance()
					.loadCards("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards1.txt");
			saveCards(cardArray, 1);

			cardArray = CAHUtil.getInstance()
					.loadCards("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/bcards2.txt");
			saveCards(cardArray, 1);

			cardArray = CAHUtil.getInstance()
					.loadCards("/Users/michaelmorris/Documents/workspace/Test/Cards Against Humanity/wcards.txt");
			saveCards(cardArray, 2);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// used to build the cards in the db
	private void saveCards(String[] cardArray, Integer cardTypeId) {

		for (int i = 0; i < cardArray.length; i++) {

			dao.addCard(cardArray[i], cardTypeId);
		}
	}

	public Deck buildBlackDeck() {

		// if (blackDeck == null)
		return buildDeck(BLACK_CARD);
		// System.out.println("[DEBUG] Number of cards: " + blackDeck.size());
	}

	public Deck buildWhiteDeck() {

		// if (whiteDeck == null)
		return buildDeck(WHITE_CARD);
		// System.out.println("[DEBUG] Number of cards: " + whiteDeck.size());
	}

	private Deck buildDeck(Integer deckType) {

		Deck deck = dao.getDeck(deckType);
		return deck;
		/*if (blackDeck == null || whiteDeck == null) {
			Deck deck = dao.getDeck(deckType);
			if (deckType == BLACK_CARD)
				blackDeck = deck;
			else
				whiteDeck = deck;
		}*/
	}

	/*
	 * @Deprecated public void shuffleDecks() {
	 * 
	 * whiteDeck.shuffleDeck(); blackDeck.shuffleDeck(); }
	 * 
	 * // This method should move to the DealerService
	 * 
	 * @Override
	 * 
	 * @Deprecated public Card drawCard(Integer cardTypeId) {
	 * 
	 * Card card = drawCards(cardTypeId, 1).iterator().hasNext() ? drawCards(
	 * cardTypeId, 1).iterator().next() : null;
	 * 
	 * return card; }
	 * 
	 * // This method should move to the DealerService
	 * 
	 * @Override
	 * 
	 * @Deprecated public Collection<Card> drawCards(Integer cardTypeId, Integer
	 * numberOfCards) {
	 * 
	 * Collection<Card> cards = null; if (cardTypeId == BLACK_CARD) cards =
	 * blackDeck.dealCards(numberOfCards); else cards =
	 * whiteDeck.dealCards(numberOfCards);
	 * 
	 * return cards; }
	 */

}
