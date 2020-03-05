import java.util.*;
import java.util.concurrent.*;

public class Tournament {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1]
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Total processors: " + processors);
        System.out.println("Please enter the number of players: ");
        int numberOfPlayer = sc.nextInt();
        ExecutorService execute = Executors.newFixedThreadPool(processors);
        Player[] players = new Player[numberOfPlayer];
        //used to wait for RPS to finish in order to run winner thread

        //set up players
        //0-rock 1-paper 2-scissors
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i + 1);
        }

        System.out.println("Created " + players.length + " players!");

        //add players to list

        List<Player> listPlayers = new ArrayList<>(Arrays.asList(players));
        List<Player> arrayListPlayers = Collections.synchronizedList(listPlayers);

        WinnerThread winThread = new WinnerThread(arrayListPlayers, numberOfPlayer, execute);
        winThread.StartGame();
    }
}
