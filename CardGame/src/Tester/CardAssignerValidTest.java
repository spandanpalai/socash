package Tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import CardGame.gameBoard;
import cardGame_player.Player;
import cardgame_functions.GameFunctions;
import junit.framework.Assert;

public class CardAssignerValidTest {
	@Test
	public void gameBoardtest() {
		gameBoard.main(null);
	}
	
	@Test
	public void cardAssignerValidTest() {
		ArrayList<String> CardsDeck = new ArrayList<String>(Arrays.asList("H-A","H-K","H-Q","H-J","H-10","H-9","H-8","H-7","H-6","H-5","H-4","H-3","H-2","C-A","C-K","C-Q","C-J","C-10","C-9","C-8","C-7","C-6","C-5","C-4","C-3","C-2","S-A","S-K","S-Q","S-J","S-10","S-9","S-8","S-7","S-6","S-5","S-4","S-3","S-2","D-A","D-K","D-Q","D-J","D-10","D-9","D-8","D-7","D-6","D-5","D-4","D-3","D-2"));
		List<Player> particpants = new ArrayList<Player>();
		particpants.add(new Player("Spandan"));
		particpants.add(new Player("Satyam"));
		particpants.add(new Player("Tiwari"));
		particpants.add(new Player("Baral"));
		GameFunctions game = new GameFunctions();
		List x = game.cardAssigner(particpants, CardsDeck);
		Player test1 = (Player) x.get(0);
		Assert.assertNotNull(test1.getCardsDrawn());
	}
	
	@Test
	public void StartGameValidTest() {
		ArrayList<String> CardsDeck = new ArrayList<String>(Arrays.asList("H-A","H-K","H-Q","H-J","H-10","H-9","H-8","H-7","H-6","H-5","H-4","H-3","H-2","C-A","C-K","C-Q","C-J","C-10","C-9","C-8","C-7","C-6","C-5","C-4","C-3","C-2","S-A","S-K","S-Q","S-J","S-10","S-9","S-8","S-7","S-6","S-5","S-4","S-3","S-2","D-A","D-K","D-Q","D-J","D-10","D-9","D-8","D-7","D-6","D-5","D-4","D-3","D-2"));
		List<Player> particpants = new ArrayList<Player>();
		particpants.add(new Player("Spandan"));
		particpants.add(new Player("Satyam"));
		particpants.add(new Player("Tiwari"));
		particpants.add(new Player("Baral"));
		GameFunctions game = new GameFunctions();
		List x = game.StartGame(game.cardAssigner(particpants, CardsDeck));
		Player test1 = (Player) x.get(0);
		Assert.assertNotNull(test1.getStatus());
	}
	
	@Test
	public void TieBreakerTest() {
		ArrayList<String> CardsDeck = new ArrayList<String>(Arrays.asList("H-A","H-K","H-Q","H-J","H-10","H-9","H-8","H-7","H-6","H-5","H-4","H-3","H-2","C-A","C-K","C-Q","C-J","C-10","C-9","C-8","C-7","C-6","C-5","C-4","C-3","C-2","S-A","S-K","S-Q","S-J","S-10","S-9","S-8","S-7","S-6","S-5","S-4","S-3","S-2","D-A","D-K","D-Q","D-J","D-10","D-9","D-8","D-7","D-6","D-5","D-4","D-3","D-2"));
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
		List<Player> particpants = new ArrayList<Player>();
		particpants.add(new Player("Spandan"));
		particpants.add(new Player("Satyam"));
		particpants.add(new Player("Tiwari"));
		particpants.add(new Player("Baral"));
		GameFunctions game = new GameFunctions();
		Player x = game.TieBreaker(particpants, remainingCardNumberValues);
		Assert.assertNotNull(x);
	}
}
