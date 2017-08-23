public class Player {
    private int health;
    private int strength;
    private int gold;
    private int lives;
    private int level;
    private int maxHealth;
    private int exp;

    public Player() {
        health = 100;
        strength = 1;
        gold = 50;
        lives = 3;
        level = 1;
        maxHealth = 100;
        exp = 0;
    }

    //WHEN A SAVE GAME IS LOADED, LOADS THE STATS
    public void newHealth(int newHealth) {
        health = newHealth;
    }

    public void newStrength(int newStrength) {
        strength = newStrength;
    }

    public void newGold(int newGold) {
        gold = newGold;
    }
    
    public void newExp(int newExp) {
        exp = newExp;
    }

    public void newLives(int newLives) {
        lives = newLives;
    }

    public void newLevel(int newLevel) {
        level = newLevel;
    }

    //RETURN SHIT
    public void takeDmg(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public void newMaxHealth(int newMaxHealth) {
        maxHealth = newMaxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public int getGold() {
        return gold;
    }
    
    public int getExp() {
        return exp;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

}
