package cardgame_functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import cardGame_player.Player;

public class GameFunctions {
	
	public void cardShuffle(List<String> deck) {
		Collections.shuffle(deck);
	}
	
	public List<Player> cardAssigner(List<Player> participant,List<String> deck) {
		//ArrayList<String> c = new ArrayList<String>(Arrays.asList("H-A","H-K","H-Q","H-J","H-10","H-9","H-8","H-7","H-6","H-5","H-4","H-3","H-2","C-A","C-K","C-Q","C-J","C-10","C-9","C-8","C-7","C-6","C-5","C-4","C-3","C-2","S-A","S-K","S-Q","S-J","S-10","S-9","S-8","S-7","S-6","S-5","S-4","S-3","S-2","D-A","D-K","D-Q","D-J","D-10","D-9","D-8","D-7","D-6","D-5","D-4","D-3","D-2"));
		this.cardShuffle(deck);
		Random rand = new Random();
		System.out.println("Deck in assigner is"+deck);
		List<String> prev = new ArrayList<String>();
		//List<Set<String>> players = new ArrayList<Set<String>>();
		for(Player p:participant) {
			Set<String> cardsSelected = new HashSet<String>();
			while(cardsSelected.size()<3) {
				Integer number1 = Integer.valueOf(rand.nextInt(51));
				String card = deck.get(number1);
				if(!prev.contains(card)) {
					cardsSelected.add(card);
					};			
			}
			p.setCardsDrawn(cardsSelected);
			prev.addAll(cardsSelected);
		}
//		while(players.size()<4) {
//			Set<String> cardsSelected = new HashSet<String>();
//			while(cardsSelected.size()<3) {
//				Integer number1 = Integer.valueOf(rand.nextInt(51));
//				System.out.print(" :"+(number1));
//				String card = deck.get(number1);
//				if(!prev.contains(card)) {
//					cardsSelected.add(card);
//			};			
//			}
//			players.add(cardsSelected);
//			prev.addAll(cardsSelected);
//		}
		return participant;
	}
	
	
	public List<Player> StartGame(List<Player> participant) {
			for(Player p:participant) {
			
			List<String> cards = new ArrayList<String>(p.getCardsDrawn());
			List<String> cardsRawValue = cards.stream().map(x->x.split("-")[1]).collect(Collectors.toList());
			List<Integer> CardNumberValues = cards.stream().map(x->{
				String temp = x.split("-")[1];
				if(Arrays.asList("K","Q","J").contains(temp)){
					return 10;
				}
				else if(temp.equals("A")) {
					return 1;
				}else {
					return Integer.valueOf(temp);
				}
				
			}).collect(Collectors.toList());
			Collections.sort(CardNumberValues);
			if(Collections.frequency(CardNumberValues, CardNumberValues.get(0))==3) {
				if(!cardsRawValue.containsAll(Arrays.asList("K","Q","J","10"))) {
					p.setStatus(30);
				}
				else {
					List x =cardsRawValue.stream().distinct().collect(Collectors.toList());
					p.setStatus(x.size()==3?10:x.size()==2?20:30);
				}
				
			}
			else if(CardNumberValues.get(0)+1==CardNumberValues.get(1) & CardNumberValues.get(0)+2==CardNumberValues.get(2)) {
				 p.setStatus(25);
			 }
			else if(CardNumberValues.stream().distinct()
	 				.filter(x-> CardNumberValues.stream().filter(b->b.equals(x)).count()==2).count()==1) {
				if(cardsRawValue.stream().distinct()
		 				.filter(x-> cardsRawValue.stream().filter(b->b.equals(x)).count()==2).count()==1) {
					p.setStatus(20);
				}
				else {
					p.setStatus(Collections.max(CardNumberValues));
				}
				
				
			}
			else {
				p.setStatus(Collections.max(CardNumberValues));
			}
			 
		}
			return participant;
	}
	
	public Player TieBreaker(List<Player> topscorers,List<Integer> remainingDeck) {
		topscorers.forEach(player->player.setCard(remainingDeck.remove(remainingDeck.size()-1)));
		Collections.sort(topscorers, new TieBreakerComparator());
		Integer maxScore = topscorers.get(0).getCard();
		System.out.println("                   -------ROUND--------");
		topscorers.forEach(a->System.out.println("                       "+a.getName()+"::"+a.getCard()));
		System.out.println();
		System.out.println();
		List topScorers2 = topscorers.stream().filter(a->a.getCard()==maxScore).collect(Collectors.toList());
		if(topScorers2.size()==1) {
			return (Player) topScorers2.get(0);
		}else {
			return this.TieBreaker(topScorers2, remainingDeck);
		}
	}
}
