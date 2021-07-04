package cardGame_player;

import java.util.Set;

public class Player {
	
	private String name;
	private Integer status;
	private Set<String> cardsDrawn;
	private Integer TieCard;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Set<String> getCardsDrawn() {
		return cardsDrawn;
	}
	public void setCardsDrawn(Set<String> cardsDrawn) {
		this.cardsDrawn = cardsDrawn;
	}
	public Integer getCard() {
		return TieCard;
	}
	public void setCard(Integer card) {
		this.TieCard = card;
	}
	public Player(String name) {
		super();
		this.name = name;
	}
	
	
}
