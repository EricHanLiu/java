import java.util.*;
public class Shop {
    private int gold;
    private int healthUpgrade;
    private int huCost;
    private int healthPotion;
    private int hpCost;
    private int strengthUpgrade;
    private int level;
    private int health;
    private int suCost;

    public Shop(int newLevel) {
        level = newLevel;
        healthUpgrade = 10 * level; //Increases max health by default 10 hp.
        hpCost = 5 * level; //Increases the cost of a potion by 5 per level up.
    }

    public void showItems(Player player) {
        Scanner stdin = new Scanner(System.in);
        String keepShopping;
        int item;
        
        do {
            //These two variables change every loop, which is why they need to be in the loop.
            huCost = healthUpgrade * 10;
            suCost = 100 * player.getStrength();
            strengthUpgrade = player.getStrength();
            healthPotion = player.getMaxHealth() / 2 - 30; //Increases potions by 5 for every upgrade.
            health = player.getHealth();
            gold = player.getGold();

            System.out.println("WELCOME TO THE LIU SHOP!");
            System.out.println("Your health: " + health);
            System.out.println("Your gold: " + gold);
            System.out.println("What would you like to buy?");
            System.out.println("    (1) Health Upgrade (" + healthUpgrade + " health): " + huCost + " gold.");
            System.out.println("    (2) Health Potion (" + healthPotion + " health): " + hpCost + " gold.");
            System.out.println("    (3) Strength Upgrade (" + strengthUpgrade + " strength): " + suCost + " gold."); 
            System.out.println("    (4) LEAVE SHOP.");
            item = stdin.nextInt();
            if (item == 1) { //HEALTH UPGRADE
                System.out.println("Would you like to buy a (Health Upgrade)? (Y/N)");
                if (stdin.next().equalsIgnoreCase("Y") && gold >= huCost) {
                    //code to increase max health, and heals fully.
                    player.newHealth(player.getMaxHealth() + healthUpgrade); //Changes current health (heal).
                    player.newMaxHealth(player.getHealth()); //Changes max health to current health.
                    player.newGold(player.getGold() - huCost); //Subtracts some gold.
                    System.out.println("Successfully bought a (Health Upgrade)!");
                    System.out.println();
                } else if (gold < huCost) {
                    System.out.println("Not enough gold!");
                }
            } else if (item == 2) { //HEALTH POTION
                System.out.println("Would you like to buy a (Health Potion)? (Y/N)");
                if (stdin.next().equalsIgnoreCase("Y") && gold >= hpCost) {
                    //Makes sure user can't buy potions to increase his health if he's already maxed.
                    if (player.getMaxHealth() - player.getHealth() == 0) {
                        System.out.println("You can't buy more potions! You're maxed out!");
                    } else if (player.getMaxHealth() - player.getHealth() < healthPotion) {
                        //Fills to max health if you're missing less than 20 health.
                        player.newHealth(player.getMaxHealth());
                        System.out.println("Successfully bought a (Health Potion)!");
                        player.newGold(player.getGold() - hpCost); //Subtracts some gold.
                    } else {
                        //code to heal health.
                        player.newHealth(player.getHealth() + healthPotion); //Changes current health (heal).
                        player.newGold(player.getGold() - hpCost); //Subtracts some gold.
                        System.out.println("Successfully bought a (Health Potion)!");
                        System.out.println();
                    }
                } else if (gold < hpCost) {
                    System.out.println("Not enough gold!");
                }
            } else if (item == 3) { //STRENGTH UPGRADE
                System.out.println("Would you like to buy a (Strength Upgrade)? (Y/N)");
                if (stdin.next().equalsIgnoreCase("Y") && gold >= suCost) {
                    //code to add strength points.
                    player.newStrength(player.getStrength() + strengthUpgrade); 
                    player.newGold(player.getGold() - suCost); //Subtracts some gold.
                    System.out.println("Successfully bought a (Strength Upgrade)!");
                    System.out.println();
                } else if (gold < suCost) {
                    System.out.println("Not enough gold!");
                }
            } else if (item == 4) {
                break;
            }
            System.out.println("Keep shopping? (Y/N)");
            keepShopping = stdin.next();
            System.out.println();
        } while (keepShopping.equalsIgnoreCase("Y"));
        System.out.println("Leaving shop...");
    }
}