import java.util.ArrayList;
import java.util.List;

public class RPSGame implements Runnable {

    final List<Player> listPlayers;
    Player currentPlayer;

    public RPSGame(List<Player> listPlayers, Player currentPlayer) {
        this.listPlayers = new ArrayList<>(listPlayers);
        this.currentPlayer = currentPlayer;
    }

    public void run() {
            System.out.println("Traversing list");
            for (Player opponentPlayer : listPlayers) {
                //do not play self
                if (currentPlayer.getPlayerNumber() != opponentPlayer.getPlayerNumber()) {
                    //changes score from within
                    //deadlock here
                    currentPlayer.checkWinningHand(opponentPlayer);
                }
       }
        //add player that played everyone to list for winner thread removal
        System.out.println("Player " + currentPlayer.getPlayerNumber() + " score: " + currentPlayer.getScore());
    }
}
