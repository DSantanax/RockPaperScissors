
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class WinnerThread  {
    List<Player> listPlayers;
    int totalPlayers;

    public WinnerThread(List<Player> list, int numberOfPlayer) {
        this.listPlayers = list;

        this.totalPlayers = numberOfPlayer;
    }
    public void StartGame() {
            //loop until done
        for(int i =0; i < totalPlayers; i++){
            ForkJoinPool pool = new ForkJoinPool();
        for(Player player : listPlayers){
            player.setGesture();
        }
        pool.invoke(new RPSGame(listPlayers, 0, listPlayers.size()));


        System.out.println("Remaining players: " + listPlayers.size());
            //remove last random player else list is 1 return winner
            if(listPlayers.size() > 1) {
                Player minPlayer = listPlayers.get(0);
                for (Player player : listPlayers) {
                    minPlayer = (minPlayer.getScore() < player.getScore()) ? minPlayer : player;
                }
                System.out.println("Player: " + minPlayer.getPlayerNumber() + " ELIMINATED! Score: " + minPlayer.getScore());
                //eliminated
                listPlayers.remove(minPlayer);
            }
        }
    }
}