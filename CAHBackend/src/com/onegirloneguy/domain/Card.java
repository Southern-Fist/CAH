package com.onegirloneguy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "card_deck")
@NamedQueries({

		@NamedQuery(name = "Card.findAll", query = "select c from Card c"),
		@NamedQuery(name = "Card.findByType", query = "select c from Card c where c.cardType = :typeId") })


public class Card {

	public static final String FIND_ALL = "Card.findAll";
	public static final String FIND_BY_TYPE = "Card.findByType";
	
	private Integer cardId;
	private String cardString;
	private CardType cardType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardString() {
		return cardString;
	}

	public void setCardString(String cardString) {
		this.cardString = cardString;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cardTypeId")
	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

}
