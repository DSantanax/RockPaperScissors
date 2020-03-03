import java.util.List;
import java.util.concurrent.RecursiveAction;

public class RPSGame extends RecursiveAction {

    List<Player> listPlayers;
    int start;
    int finish;

    public RPSGame(List<Player> listPlayers, int start, int finish) {
        this.listPlayers = listPlayers;
        this.start = start;
        this.finish = finish;
    }

    public void startGame() {
        //threads loop from start to finish only
        System.out.println(start + " " + finish);
            for (int i = start; i < finish; i++) {
                Player currentPlayer = listPlayers.get(i);
                for (Player opponentPlayer : listPlayers) {
                    //do not play self
                    if (currentPlayer.getPlayerNumber() != opponentPlayer.getPlayerNumber()) {
                        //changes score from within
                            currentPlayer.checkWinningHand(opponentPlayer);
                    }
                }
                //add player that played everyone to list for winner thread removal
                System.out.println("Player " + currentPlayer.getPlayerNumber() + " score: " + currentPlayer.getScore());
            }
            //TODO: threads need to wait for winner thread to repeat
    }
    @Override
    protected void compute() {
        if(finish-start < 250){
            startGame();
        }
        else{
            int mid  = (finish+start)/2;
            invokeAll(new RPSGame(listPlayers, start, mid),
            new RPSGame(listPlayers, mid, finish));
        }
    }
}
