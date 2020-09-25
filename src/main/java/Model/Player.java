package Model;

import org.jetbrains.annotations.NotNull;

public abstract class Player {
    @NotNull("name cannot be null")
    protected String name;
    protected int attack;
    protected int hPoints;
    protected int defense;

    public Player(String name, int attack, int hPoints, int defense) {
        this.setName(name);
        this.setAttack(attack);
        this.sethPoints(hPoints);
        this.setDefense(defense);
    }


    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int gethPoints() {
        return hPoints;
    }

    public void sethPoints(int hPoints) {
        this.hPoints = hPoints;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
