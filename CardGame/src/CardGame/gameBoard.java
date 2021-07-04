package CardGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



import cardGame_player.Player;
import cardgame_functions.GameFunctions;
import cardgame_functions.scoreComparator;

public class gameBoard {

	
	public static void main(String[] args) {
		System.out.println("*******************************WELCOME TO DIGITAL POKER************************************");
		ArrayList<String> CardsDeck = new ArrayList<String>(Arrays.asList("H-A","H-K","H-Q","H-J","H-10","H-9","H-8","H-7","H-6","H-5","H-4","H-3","H-2","C-A","C-K","C-Q","C-J","C-10","C-9","C-8","C-7","C-6","C-5","C-4","C-3","C-2","S-A","S-K","S-Q","S-J","S-10","S-9","S-8","S-7","S-6","S-5","S-4","S-3","S-2","D-A","D-K","D-Q","D-J","D-10","D-9","D-8","D-7","D-6","D-5","D-4","D-3","D-2"));
		System.out.println();
		System.out.println();
		System.out.println("*******************************  PLAYERS BEING ADDED   ************************************");
		List<Player> particpants = new ArrayList<Player>();
		particpants.add(new Player("Spandan"));
		particpants.add(new Player("Satyam"));
		particpants.add(new Player("Tiwari"));
		particpants.add(new Player("Baral"));
		System.out.println();
		System.out.println("Adding Player......................");
		System.out.println();
		GameFunctions gameFunction = new GameFunctions();
		System.out.println("Loading controller..................");
		System.out.println();
		System.out.println("we are almost ready.................");
		System.out.println();
		System.out.println("*******************************  NAME OF PARTICIPANTS  ************************************");
		System.out.println();
		particpants.forEach(p->System.out.println("                            NAME:"+p.getName()));
		System.out.println();
		System.out.println("Unshuffled cards"+CardsDeck);
		System.out.println();
		System.out.println("*******************************  LETS US SHUFFLE THE DECK  ************************************");
		gameFunction.cardShuffle(CardsDeck);
		System.out.println();
		System.out.println("shuffled cards"+CardsDeck);
		System.out.println("*******************************  ASSIGNING CARDS TO EACH PARTICIPANT  ************************************");
		particpants = gameFunction.cardAssigner(particpants, CardsDeck);
		particpants.forEach(player-> CardsDeck.removeAll(player.getCardsDrawn()));
		System.out.println();
		
		System.out.println("remaining cards::::"+CardsDeck.size());
		
		System.out.println("*******************************  SHOW THE CARDS TO CALCULATE SCORES  ************************************");
		particpants = gameFunction.StartGame(particpants);
		System.out.println();
		particpants.forEach(p->System.out.println("                            NAME: "+p.getName()+" AND CARDS: "+p.getCardsDrawn()));
		System.out.println();
		System.out.println();
		//changing cards value to number for tiebreaker
		List<Integer> remainingCardNumberValues = CardsDeck.stream().map(x->{
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
		System.out.println();
		System.out.println("*******************************  SCORE BOARD  ************************************");
		Collections.sort(particpants, new scoreComparator());
		Integer maxScore = particpants.get(0).getStatus();
		particpants.forEach(a->System.out.println("                          "+a.getName()+"::"+a.getStatus()));
		List topScorers = particpants.stream().filter(a->a.getStatus()==maxScore).collect(Collectors.toList());
		System.out.println();
		System.out.println();
		System.out.println("*******************************  WINNER BOARD  ************************************");
		if(topScorers.size()==1) {
			System.out.println();
			Player winner = (Player)topScorers.get(0);
			System.out.println("Congratulations "+winner.getName()+" !!! You won the Grand Prize" );
			System.out.println();
			System.out.println();
		}
		else {
			System.out.println("*******************************  WE HAVE A TIE , SO STARTING TIE BREAKER GAME ************************************");
			Player winner = gameFunction.TieBreaker(topScorers, remainingCardNumberValues);
			System.out.println();
			System.out.println();
			
			System.out.println("Finally, Congratulations "+winner.getName()+" !!! You won the Grand Prize");
		}
	}
}
