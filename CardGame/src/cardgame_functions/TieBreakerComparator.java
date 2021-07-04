package cardgame_functions;

import java.util.Comparator;

import cardGame_player.Player;

public class TieBreakerComparator implements Comparator<Player> {
	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		if (o1.getCard() == o2.getCard())
        return 0;
    else if (o1.getCard() < o2.getCard())
        return 1;
    else
        return -1;
	}
}
