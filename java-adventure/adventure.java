/* 
 * Text based adventure game similar to Zelda. ATM there are no battles implemented,
 * you either take a random damage and get a random gold amount or have no encounters.
 * The random gold is based off your strength, while the random damage will eventually
 * scale with your health and level. 
 * Need to implement: Level system, fight system, scaling system with items etc.
 */
import java.util.*;
import java.io.*;
public class adventure {
    public static void main(String [ ] args) {
        //VARIABLES
        Scanner stdin = new Scanner(System.in);
        String name = "";
        Player player = new Player();
        Shop shop = null;
        int startChoice;
        int i = 0;
        String line = null;

        //Start-up screen.
        System.out.println("LIU ADVENTURE GAME 3000");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("(1): Start New Game");
        System.out.println("(2): Continue Existing Game");
        System.out.println("(3): How To Play");
        System.out.println("(4): Exit Program");
        startChoice = stdin.nextInt();
        System.out.println();

        /* Stat explaination: Max health is by default 100, can be increased by buying
         * health upgrades, and can be replenished by buying health potions. These
         * items scale with the level of the player. Strength is by default 1, and the
         * damage you deal is a random number from a min of your strength * 5. So, if
         * user strength is 1, can deal between 1-5 damage. AI health and damage will
         * scale with that of the player. Strength can be increased by buying a strength
         * upgrade from the shop, and the cost of the strength upgrade will remain the same
         * but the amount you must buy at one time will increase to essentially 'increase' the
         * price of an upgrade although it remains the same.
         */

        //NEW GAME
        if (startChoice == 1) {
            //CODE
            sleep(500);
            System.out.println("What's your name?");
            name = stdin.next();
            sleep(1000);
            System.out.println();
            System.out.println("Welcome to the world of Liu.");
            System.out.println("You will control " + name + " in your quest to greatness.");
            System.out.println();
            showStats(player, name);
            sleep(1500);

            //Gameplay method.
            gamePlay(stdin, player, name, shop);

        } else if (startChoice == 2) { //RESUME GAME
            //READ STATS FROM SAVE FILE
            try {
                BufferedReader read = new BufferedReader(new FileReader("save.txt"));
                name = read.readLine();
                line = read.readLine();
                player.newHealth(Integer.parseInt(line));
                line = read.readLine();
                player.newMaxHealth(Integer.parseInt(line));
                line = read.readLine();
                player.newStrength(Integer.parseInt(line));
                line = read.readLine();
                player.newGold(Integer.parseInt(line));
                line = read.readLine();
                player.newExp(Integer.parseInt(line));
                line = read.readLine();
                player.newLevel(Integer.parseInt(line));
                line = read.readLine();
                player.newLives(Integer.parseInt(line));
                read.close();
            } catch (Exception ex) {
                System.out.println("The save file is corrupted, or there is currently no existing game!");
                System.exit(0);
            }
            showStats(player, name);
            //Gameplay method.
            sleep(1500);
            gamePlay(stdin, player, name, shop);
        } else if (startChoice == 3) { //Config and instructions.
            System.out.println("Instructions of the game.");
            System.out.println("You will control a hero who will gain gold and XP from adventuring.");
            System.out.println("The game will scale infinitely with your hero.");
            System.out.println("There is no level, gold, or strength cap.");
            System.out.println("As you level up you will gain skill points.");
            System.out.println("These points can be used to level up one of your stats.");
            System.out.println("Additionally, these stats can be leveled up by purchasing upgrades from the shop.");
            System.out.println("The shop sells items for the gold you will gain from your adventuring.");
            System.out.println("How do the stats work?");
            System.out.println("Strength:");
            System.out.println("    Strength affects the gold and exp you will gain from adventuring.");
            System.out.println("    Strength upgrades from the shop scale with your current strength.");
            System.out.println("    In other words, they cost the same but can only be bought in greater values.");
            System.out.println("Level:");
            System.out.println("    As you level up, you will be allowed to level a skill.");
            System.out.println("    The skill you level will be upgraded based on a multiple of your current level.");
            System.out.println("    This means that at level 7, skilling strength will upgrade your strength by 7.");
            System.out.println("Damage:");
            System.out.println("    The damage you take from adventuring increases with your level.");
            System.out.println("    There is no way to counter-act this increase other than increasing your max-hp.");
            System.out.println("Max-Hp:");
            System.out.println("    Everytime you buy a max-hp upgrade from the shop, your health is replenished.");
            System.out.println("    Upgrading your max-hp scales with your current level * 10.");
            System.out.println("Exp:");
            System.out.println("    The amount of exp you earn scales inversely with your level.");
            System.out.println("    This means that at early levels, you will earn a lot per encounter.");
            System.out.println("    At later levels, you will earn a lot less, to a minimum of 3xp per encounter.");
            System.out.println("Luck:");
            System.out.println("    Not yet implemented, but will soon increase the xp you earn per encounter.");
            System.out.println("    Will also affect the prices of items in the shop.");
            System.out.println("Adventuring consists of the following:");
            System.out.println("    Taking a certain amount of damage.");
            System.out.println("    Earning a certain amount of gold.");
            System.out.println("    Earning a certain amount of xp.");
            System.out.println("    There is a chance that you will find a treasure chest.");
            System.out.println("    This chest will contain a large amount of gold, and will soon contain items as well.");
        }

    }

    //Actual content of the game.
    public static void gamePlay(Scanner stdin, Player player, String name, Shop shop) {
        int action;
        String wannaSave;
        int skill = 0;
        do {
            int chanceOfEnc = (int) (Math.random() * 11);
            //1 out of 25 chance to get a treasure, worth a huge amount of gold.
            int chanceOfTreasure = (int) (Math.random() * 25); 

            //Options, what you can do.
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("    (1) Advance a step.");
            System.out.println("    (2) Access the shop.");
            System.out.println("    (3) Check your character's stats.");
            System.out.println("    (4) Save and quit.");
            action = stdin.nextInt();
            System.out.println();
            sleep(500);
            if (action == 1) {
                System.out.println("Walking...");
                sleep(500);
                //Sets a 8/10 chance of an encounter.
                if (chanceOfEnc > 2 && player.getHealth() > 0) { 
                    //CODE FOR ENCOUNTER (BATTLE)
                    //Randomized variables for random events.
                    int ranDmg = (int) + (5 + Math.random() * 5 * (player.getLevel() / 2));
                    //Gold gained scales up with strength. 
                    int ranGold = (int) + (5 + Math.random() * 5 * player.getStrength());
                    //Exp scales down with your level (soon will also be luck).
                    int ranExp = (int) + (3 + Math.random() * 10 / player.getLevel());

                    //Register and update the stats.
                    player.takeDmg(ranDmg); 
                    player.newExp(player.getExp() + ranExp);
                    
                    //What shows on screen.
                    System.out.println("Ow! You took " + ranDmg + " damage!");
                    if (chanceOfTreasure == 0) {
                        System.out.println("Whoa! You found an treasure chest!");
                        System.out.println("It contained " + ranGold * 100 + " gold!");
                        player.newGold(player.getGold() + (ranGold * 100));
                    } else {
                        System.out.println("You found " + ranGold + " gold!");
                        player.newGold(player.getGold() + ranGold);
                    }
                    System.out.println("You gained " + ranExp + " exp!");

                    //Level up.
                    if (player.getExp() >= 100) {
                        sleep(500);
                        System.out.println();
                        System.out.println("You've leveled up!");
                        player.newLevel(player.getLevel() + 1);
                        System.out.println("You're now level " + player.getLevel() + "!");

                        //Skill point raise.
                        System.out.println("You've gained a skill point!");
                        do {
                            System.out.println("Which stat would you like to raise?");
                            System.out.println("    (1) Strength");
                            System.out.println("    (2) Max Health");
                            skill = stdin.nextInt();
                            System.out.println();
                            //Future stat raises: luck, dex, etc.
                            if (skill == 1) {
                                System.out.println("Successfully raised strength by " + player.getLevel() + "!");
                                player.newStrength(player.getStrength() + player.getLevel());
                            } else if (skill == 2) {
                                System.out.println("Successfully raised max health by " + player.getLevel() * 10 + "!");
                                player.newMaxHealth(player.getMaxHealth() + (player.getLevel() * 10));
                                player.newHealth(player.getMaxHealth());
                            } else {
                                System.out.println("That's not a skill!");
                            }
                        } while (skill > 2 || skill < 1);

                        //Resets exp to 0 and adds the remainder.
                        player.newExp(player.getExp() - 100);
                    }
                } else if (chanceOfEnc <= 2) {
                    System.out.println("No encounters!");
                    System.out.println("You found 5 gold!");
                    player.newGold(player.getGold() + 5);
                } 
                if (player.getHealth() <= 0) { //User passes out!
                    //User loses 30% of current gold and a life.
                    System.out.println();
                    sleep(500);
                    System.out.println("You have been knocked out!");
                    System.out.print("You have lost ");
                    System.out.println((int) (player.getGold() * .3) + " gold, as well as 1 life.");
                    player.newGold(player.getGold() - (int) (player.getGold() * .3));
                    player.newHealth(player.getMaxHealth());
                    player.newLives(player.getLives() - 1);
                    sleep(500);
                    if (player.getLives() == 0) {
                        //Player loses the game.
                        System.out.println("You're out of lives! You've lost the game.");
                        System.out.println("You may resume from your last save point.");
                        System.exit(0);
                    }
                }
            } else if (action == 2) { //Shop.
                shop = new Shop(player.getLevel());
                shop.showItems(player);
            } else if (action == 3) { //Stats.
                //Shows character stats.
                showStats(player, name);               
            } else if (action == 4) { //Save and quit.
                System.out.println("Would you like to save your progress? (Y/N)");
                wannaSave = stdin.next();
                if (wannaSave.equalsIgnoreCase("Y")) { //Saves the game and quits.
                    saveGame(player, name);
                    System.out.println("Saving...");
                    System.out.println("Successfully saved the game!");
                    sleep(500);
                    System.out.println("Shutting down program...");
                    sleep(1000);                    
                    System.exit(0);
                } else {
                    System.out.println("Game was not saved.");
                    System.exit(0);
                }
            } else { //Bad input from user.
                System.out.println("That's not an option!");
            }
            sleep(500);
        } while (action != 4);
    }

    public static void showStats(Player player, String name) {
        System.out.println("Hero: " + name);
        System.out.println("Max Health: " + player.getMaxHealth());
        System.out.println("Current Health: " + player.getHealth());
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Gold: " + player.getGold());
        System.out.println("Exp: " + player.getExp());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Lives: " + player.getLives());
    }

    public static void saveGame(Player player, String name) {
        //Will store the user's stats into save.txt.
        try {
            //WRITES THEIR STATS TO "save.txt"
            BufferedWriter write = new BufferedWriter(new FileWriter("save.txt"));
            write.write(name);
            write.newLine();
            write.write(String.valueOf(player.getHealth()));
            write.newLine();
            write.write(String.valueOf(player.getMaxHealth()));
            write.newLine();
            write.write(String.valueOf(player.getStrength()));
            write.newLine();
            write.write(String.valueOf(player.getGold()));
            write.newLine();
            write.write(String.valueOf(player.getExp()));
            write.newLine();
            write.write(String.valueOf(player.getLevel()));
            write.newLine();
            write.write(String.valueOf(player.getLives()));
            write.close();
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public static void sleep(int length) {
        try {
            Thread.sleep(length);
        } catch (Exception ex) {
            System.exit(0);
        }
    }
}