package Swingy.Model;

public class Enemy extends Player{
    private Extras extras;
    public Enemy(String name, int attack, int hPoints, int defense, Extras extras) {
        super(name, attack, hPoints, defense);
        this.setExtras(extras);
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }
}
