package Model;

public class NewHero {
    protected String name;
    protected int attack;
    protected int defense;
    protected int hPoints;
    protected String heroClass;
    protected int level;
    protected int exp;
    protected Weapon weapon;
    protected Armor armor;
    protected Helm helm;

    public Hero hero(){
        return new Hero(name, attack, hPoints, defense, level, exp, heroClass, weapon, armor, helm);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void sethPoints(int hPoints) {
        this.hPoints = hPoints;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }
}
