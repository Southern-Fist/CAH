package com.onegirloneguy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "card_type" )
public class CardType {

	private Integer cardTypeId;
	private String cardTypeDesc;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public Integer getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(final Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	public String getCardTypeDesc() {
		return cardTypeDesc;
	}
	public void setCardTypeDesc(final String cardTypeDesc) {
		this.cardTypeDesc = cardTypeDesc;
	}
	
}
