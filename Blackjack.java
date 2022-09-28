import java.util.*;


public class Blackjack{
    static Stack<Card> deck = new Stack<Card>();
    static Stack<Card> playerHand = new Stack<Card>();
    static Stack<Card> opponentHand = new Stack<Card>();

    public static void init(){

        String[] suits = {"D", "S", "H", "C"};
        String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        for (String s : suits) {
            for (String value : values) {
                deck.push(new Card(s, value, true));
            }
        }

        Collections.shuffle(deck);

    }

    public static Card draw(Stack<Card> c){
        try {
            if(c.empty()){
                c.push(deck.pop());
                c.firstElement().setFaceUp(false);
            }
            else{
                return c.push(deck.pop());
            }
        }catch (Exception e){
            System.out.println("Can't draw");
        }
        return c.push(deck.pop());
    }

    public static Boolean validHand(Stack<Card> c){
        HashMap<String, Integer> valueTable = new HashMap<String, Integer>();

        valueTable.put("A", 11);
        valueTable.put("2", 2);
        valueTable.put("3", 3);
        valueTable.put("4", 4);
        valueTable.put("5", 5);
        valueTable.put("6", 6);
        valueTable.put("7", 7);
        valueTable.put("8", 8);
        valueTable.put("9", 9);
        valueTable.put("10", 10);
        valueTable.put("J", 10);
        valueTable.put("K", 10);
        valueTable.put("Q", 10);

        int sum = 0;
        int hasAce = 0;

        for(int i = 0; i < c.size(); i++){
            if (Objects.equals(c.get(i).getValue(), "A")) hasAce++;
            sum+= valueTable.get(c.get(i).getValue());
        }

        while(sum > 21 && hasAce > 0){
            hasAce--;
            sum-= 10;
        }

        return sum <= 21;

    }

    public static int countHand(Stack<Card> c){
        HashMap<String, Integer> valueTable = new HashMap<String, Integer>();

        valueTable.put("A", 11);
        valueTable.put("2", 2);
        valueTable.put("3", 3);
        valueTable.put("4", 4);
        valueTable.put("5", 5);
        valueTable.put("6", 6);
        valueTable.put("7", 7);
        valueTable.put("8", 8);
        valueTable.put("9", 9);
        valueTable.put("10", 10);
        valueTable.put("J", 10);
        valueTable.put("K", 10);
        valueTable.put("Q", 10);

        int sum = 0;
        int hasAce = 0;

        for(int i = 0; i < c.size(); i++){
            if (Objects.equals(c.get(i).getValue(), "A")) hasAce++;
            sum+= valueTable.get(c.get(i).getValue());
        }

        while(sum > 21 && hasAce > 0){
            hasAce--;
            sum-= 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        init();
        System.out.println("Welcome to Blackjack");

        int startMoney = 1500;
        int bet =0;
        while(startMoney > 0 && deck.size() >= 4){


            if(bet == 0 ){
                System.out.println("Current money: " + startMoney);
                System.out.println("Enter bet: ");
                Scanner input = new Scanner(System.in);
                bet = input.nextInt();
            }


            if(playerHand.empty() && opponentHand.empty()){
                System.out.println("You draw.");
                draw(playerHand);

                playerHand.firstElement().setFaceUp(true);

                System.out.println("You have: " + playerHand + ", which is " +countHand(playerHand));

                if(countHand(playerHand) > 21){
                    System.out.println("You've drawn over...");
                    opponentHand.firstElement().setFaceUp(true);

                    System.out.println(("Opponent had: " + opponentHand));
                    startMoney -= bet;
                    bet = 0;
                    playerHand.clear();
                    opponentHand.clear();
                }

                System.out.println("Opponent draws.");
                draw(opponentHand);

                if(validHand(opponentHand)) System.out.println("Opponent has " + opponentHand);
                else{
                    System.out.println("Opponent has gone over.");
                    opponentHand.firstElement().setFaceUp(true);

                    System.out.println("They had: " + opponentHand);
                    startMoney += bet;
                    bet = 0;
                    playerHand.clear();
                    opponentHand.clear();
                }

            }



            System.out.println("What would you like to do?");
            System.out.println("1. Hit \n2. Stand \n3. Double Down \n4. Quit");

            Scanner input = new Scanner(System.in);
            String cmd = input.nextLine();
            switch(cmd){
                case "Hit":
                    draw(playerHand);
                    if(validHand(playerHand)) System.out.println("Your cards are: " + playerHand);
                    else{
                        System.out.println("Your cards are: " + playerHand);
                        System.out.println("You've drawn over...");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney -= bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    break;
                case "Stand":
                    while(countHand(opponentHand) <= 16){
                        System.out.println("Opponent draws.");
                        System.out.println(draw(opponentHand));
                    }
                    if(validHand(opponentHand)) System.out.println("Opponent has " + opponentHand);
                    if(countHand(opponentHand) > countHand(playerHand) && validHand(opponentHand)){
                        System.out.println("Opponent wins");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney -= bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    else if(countHand(opponentHand) < countHand(playerHand)){
                        System.out.println("You win");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney += bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    else if(countHand(opponentHand) == countHand(playerHand)){
                        System.out.println("Draw");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    else{
                        System.out.println("Opponent has gone over.");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney += bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    break;
                case "DD":
                    draw(playerHand);
                    bet *= 2;

                    if(validHand(playerHand)) System.out.println("Your cards are: " + playerHand);

                    if(countHand(opponentHand) > countHand(playerHand) && validHand(opponentHand)){
                        System.out.println("Opponent wins");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney -= bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    else if(countHand(opponentHand) < countHand(playerHand)){
                        System.out.println("You win");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney += bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }
                    else if(countHand(opponentHand) == countHand(playerHand)){
                        System.out.println("Draw");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();
                    }

                    else{
                        System.out.println("Your cards are: " + playerHand);
                        System.out.println("You've drawn over...");
                        opponentHand.firstElement().setFaceUp(true);
                        System.out.println(("Opponent had: " + opponentHand));
                        startMoney -= bet;
                        bet = 0;
                        playerHand.clear();
                        opponentHand.clear();}

                    break;
                case "Quit":
                    System.out.println("You have " + startMoney + " left. There were " + deck.size() + " cards left to draw.");
                    startMoney = 0;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cmd);
            }





        }
    }
}
