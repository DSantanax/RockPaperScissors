import java.util.Random;

public class Player {
    private static Random rand = new Random();
    private int score;
    private char handGesture;
    private int playerNumber;

    public Player(int num){
        playerNumber = num;
    }

    public synchronized int getPlayerNumber(){
        return playerNumber;
    }
    public synchronized int getScore(){return score;}

    public synchronized void setGesture() {
        int gesture = rand.nextInt(3);
        if(gesture == 0){
            handGesture = 'r';
        }
        else if(gesture == 1){
            handGesture ='p';
        }
        else{
            handGesture = 's';
        }
    }
    //can change to addScore();

    public synchronized char getHandGesture(){return handGesture;}

    public synchronized void checkWinningHand(Player p2){

        char player2Gesture = p2.getHandGesture();
        int player2Number = p2.getPlayerNumber();
        //s>p
        if(handGesture == 's' && player2Gesture == 'p'){
            System.out.println("Player: " + playerNumber + " Scissors beats  Player: " + player2Number + " Paper!");
            score++;
        }
        //r>s
        else if(handGesture == 'r' && player2Gesture == 's'){
            System.out.println("Player: " + playerNumber + " Rock beats Player: " + player2Number + " Scissors!");
            score++;
        }
        //p>r
        else if(handGesture == 'p' && player2Gesture == 'r'){
            System.out.println("Player: " + playerNumber + " Paper beats Player: " + player2Number + " Rock!");
            score++;
        }
        //tie
        else if(handGesture == player2Gesture){
            System.out.println("Tie! Player: " + playerNumber + " vs Player: " + player2Number + " Going again !");
            //do nothing
        }
        //lost
        else {
            System.out.println("Player: " + playerNumber +" lost against Player: " + player2Number);
            score--;
        }
    }
}
