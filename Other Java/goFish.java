import java.util.*;
public class goFish {
    public static void main(String [ ] args) {
        Scanner stdin = new Scanner(System.in);
        //VARIABLES OF THE GAME
        ArrayList yourHand = new ArrayList();
        ArrayList theirHand = new ArrayList();
        ArrayList deck = new ArrayList();
        int[] possibleCards = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int i = 0;
        int yourPairs = 0;
        int theirPairs = 0;
        int card;
        int j = 0;
        int you = 0;
        boolean theyHaveCard;

        rulesOfGame();
        System.out.println("Type any key when you wish to start.");
        stdin.next();
        System.out.println();
        System.out.println();
        System.out.println("*****GAME START*****");

        for (i = 0; i < possibleCards.length; i++) { //Adds four cards of each number.
            deck.add(possibleCards[i]);
            deck.add(possibleCards[i]);
            deck.add(possibleCards[i]);
            deck.add(possibleCards[i]);
        }
        Collections.shuffle(deck); //Shuffles deck.

        //YOUR CARDS
        for (i = 0; i < 7; i++) {
            yourHand.add(deck.get(i));
            deck.remove(i);
        }
        System.out.println("YOUR CARDS: " + yourHand);
        pairMade(you, yourHand, theirHand, yourPairs, theirPairs);

        //THEIR CARDS
        System.out.println();
        for (i = 0; i < 7; i++) {
            theirHand.add(deck.get(i));
            deck.remove(i);
        }
        you = 1;
        System.out.println("THEIR CARDS: [X X X X X X X]");

        pairMade(you, yourHand, theirHand, yourPairs, theirPairs);

        System.out.println();
        System.out.println("YOUR CARDS: " + yourHand);
        System.out.print("THEIR CARDS: [");
        for (i = 0; i < theirHand.size() - 1; i++) {
            System.out.print("X ");
        }
        System.out.println("X]");

        //GOFISH CODE
        do {
            theyHaveCard = false;
            //YOUR TURN
            do {
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println();
                System.out.println();
                System.out.println("***YOUR TURN***");
                System.out.print("What card do you need: ");
                card = stdin.nextInt();
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                if (theirHand.size() == 0) {
                    theyHaveCard = false;
                }
                //If they have the card you ask for, will remove from their hand, remove pair and add to total.
                for (i = 0; i < theirHand.size(); i++) { 
                    if (card == theirHand.get(i)) {
                        System.out.println("They have it!");
                        yourHand.add(theirHand.get(i));
                        theirHand.remove(i);
                        you = 0;
                        pairMade(you, yourHand, theirHand, yourPairs, theirPairs);
                        theyHaveCard = true;

                        System.out.println();
                        System.out.println("YOUR CARDS: " + yourHand);
                        System.out.print("THEIR CARDS: [");
                        for (i = 0; i < theirHand.size() - 1; i++) {
                            System.out.print("X ");
                        }
                        System.out.println("X]");
                        break;
                    } else {
                        theyHaveCard = false;
                    }
                }
            } while (theyHaveCard == true && (deck.size() > 0 || (yourHand.size() > 0 && theirHand.size() > 0)));

            if (theyHaveCard == false) {
                System.out.println();
                System.out.println("GOFISH! Draw a card.");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                yourHand.add(deck.get(0));
                System.out.println("You drew a: " + deck.get(0));
                deck.remove(0);
                you = 0;
                pairMade(you, yourHand, theirHand, yourPairs, theirPairs);
            }

            System.out.println();
            System.out.println("YOUR CARDS: " + yourHand);
            System.out.print("THEIR CARDS: [");
            for (i = 0; i < theirHand.size() - 1; i++) {
                System.out.print("X ");
            }
            System.out.println("X]");

            //THEIR TURN
            do {
                try {
                    Thread.sleep(3000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println();
                System.out.println();
                System.out.println("***THEIR TURN***");
                //Picks a random card from their hand as the one they want to get from you.
                try { //Problem with empty hand occured, need to add this to end game.
                    card = (int) theirHand.get((int) (Math.random() * theirHand.size()));
                } catch(Exception ex) {
                    System.out.println("You had: " + yourPairs + " pairs.");
                    System.out.println("They had: " + theirPairs + " pairs.");

                    if (yourPairs > theirPairs) {
                        System.out.println("Congrats! You've won the game.");
                    } else {
                        System.out.println("They've won. Too bad.");
                    }
                }
                System.out.println("They want a " + card + "!");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                if (theirHand.size() == 0) {
                    theyHaveCard = true;
                }
                //If they have the card you ask for, will remove from their hand, remove pair and add to total.
                for (i = 0; i < yourHand.size(); i++) { 
                    if (card == yourHand.get(i)) {
                        System.out.println("You have it!");
                        theirHand.add(yourHand.get(i));
                        yourHand.remove(i);
                        you = 1;
                        pairMade(you, yourHand, theirHand, yourPairs, theirPairs);
                        theyHaveCard = false;

                        System.out.println();
                        System.out.println("YOUR CARDS: " + yourHand);
                        System.out.print("THEIR CARDS: [");
                        for (i = 0; i < theirHand.size() - 1; i++) {
                            System.out.print("X ");
                        }
                        System.out.println("X]");
                        break;
                    } else {
                        theyHaveCard = true;
                    }
                }
            } while (theyHaveCard == false && (deck.size() > 0 || (yourHand.size() > 0 && theirHand.size() > 0)));

            if (theyHaveCard == true) {
                System.out.println();
                System.out.println("GOFISH! They draw a card.");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                theirHand.add(deck.get(0));
                System.out.println("They drew a: X");
                deck.remove(0);                
                you = 1;
                pairMade(you, yourHand, theirHand, yourPairs, theirPairs);
                System.out.println();
                System.out.println("YOUR CARDS: " + yourHand);
                System.out.print("THEIR CARDS: [");
                for (i = 0; i < theirHand.size() - 1; i++) {
                    System.out.print("X ");
                }
                System.out.println("X]");
            }
        } while (deck.size() > 0 || (yourHand.size() > 0 && theirHand.size() > 0));
        System.out.println("You had: " + yourPairs + " pairs.");
        System.out.println("They had: " + theirPairs + " pairs.");

        if (yourPairs > theirPairs) {
            System.out.println("Congrats! You've won the game.");
        } else {
            System.out.println("They've won. Too bad.");
        }
    }

    public static void pairMade(int you, ArrayList yourHand, ArrayList theirHand, int yourPairs, int theirPairs) {
        //Method to check if a pair can be made with your cards.
        if (you == 0) {           
            for (int i = 0; i < yourHand.size(); i++) { //LOOKS FOR A PAIR, REMOVES FROM LIST IF FOUND
                for (int j = i+1; j < yourHand.size(); j++) {
                    if (yourHand.get(i) == yourHand.get(j)) {
                        yourPairs++;
                        System.out.println("PAIR MADE: " + yourHand.get(i) + " AND " + yourHand.get(j));  
                        yourHand.remove(j);
                        yourHand.remove(i);
                        i = 0;
                        j = i+1;
                    }
                }
            }
        }

        if (you == 1) {           
            for (int i = 0; i < theirHand.size(); i++) { //LOOKS FOR A PAIR, REMOVES FROM LIST IF FOUND
                for (int j = i+1; j < theirHand.size(); j++) {
                    if (theirHand.get(i) == theirHand.get(j)) {
                        theirPairs++;
                        System.out.println("PAIR MADE: " + theirHand.get(i) + " AND " + theirHand.get(j));  
                        theirHand.remove(j);
                        theirHand.remove(i);
                        i = 0;
                        j = i+1;
                    }
                }
            }
        }
    }

    public static void rulesOfGame() {
        System.out.println("Welcome to GoFish! Coded and programmed by Eric Liu.");
        System.out.println("The purpose of this game is to make as many pairs as possible.");
        System.out.println("This can be done by asking the opponent for a card that you already possess.");
        System.out.println("YOU MAY ONLY ASK FOR A CARD YOU ALREADY POSSESS IN YOUR HAND.");
        System.out.println("At the start of the game, each player is dealt 7 cards.");
        System.out.println("From these 7 cards, pairs are made if possible.");
        System.out.println("Once pairs are made, you may ask the opponent for a card you possess.");
        System.out.println("If they have the card too (suits are disregarded), they must give you the card.");
        System.out.println("You then get another turn.");
        System.out.println("If they do not have the requested card, they say 'GOFISH', and you must draw a card.");
        System.out.println("The card you draw can be used to form a pair with the existing cards in your hand.");
        System.out.println("At the end of the game, the player with more pairs wins.");
    }
}