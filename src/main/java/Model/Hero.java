package Model;

public class Hero extends Player {
    private int level;
    private int exp;
    private String heroClass;
    private  Weapon weapon;
    private  Armor armor;
    private Helm helm;
    public Hero(String name, int attack, int hPoints, int defense, int level, int exp, String heroClass, Weapon weapon, Armor armor, Helm helm) {
        super(name, attack, hPoints, defense);
        this.setLevel(level);
        this.setExp(exp);
        this.setHeroClass(heroClass);
        this.setWeapon(weapon);
        this.setArmor(armor);
        this.setHelm(helm);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }
}
