import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WinnerThread  {
    List<Player> listPlayers;
    int totalPlayers;
    ExecutorService execute;
    public WinnerThread(List<Player> list, int numberOfPlayer, ExecutorService execute) {
        this.listPlayers = list;
        this.execute =execute;
        this.totalPlayers = numberOfPlayer;
    }
    public void StartGame() {
            //loop until done
        long start = System.nanoTime();
        for(int i = 0; i < totalPlayers; i++) {
            System.out.println("Players remaining: " + listPlayers.size());
            if (listPlayers.size() > 1) {
                for (Player player : listPlayers) {
                    player.setGesture();
                }
                System.out.println("Gestures set! Round " + (i+1));

                for (int j = 0; j < listPlayers.size(); j++) {
                    execute.submit(new RPSGame(listPlayers, listPlayers.get(j)));
                }

                System.out.println("Players in queue!");
                 execute.shutdown();
                //wait
                while (!execute.isTerminated()) {

                }

                System.out.println("RPS DONE!");

                //remove last random player else list is 1 return winner
                Player minPlayer = listPlayers.get(0);
                for (Player player : listPlayers) {
                    minPlayer = (minPlayer.getScore() < player.getScore()) ? minPlayer : player;
                }
                System.out.println("Player: " + minPlayer.getPlayerNumber() + " ELIMINATED! Score: " + minPlayer.getScore());
                //eliminated
                listPlayers.remove(minPlayer);
                System.out.println("Remaining players: " + listPlayers.size());

                execute = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            }
            else{
                System.out.println("Player: " + listPlayers.get(0).getPlayerNumber() + " winner");
                System.out.println("Total time: " + (System.nanoTime() - start)/1000 + "ms");
        }

        }
    }
}