package Swingy.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {
    @NotNull(message = "Hero Name cannot be empty!")
    protected String name;
    @NotNull
    @Min(value = 0, message="Attack should not be less than 0!")
    protected int attack;
    @Min(value = 1, message="Hit Points should not be less than 1!")
    protected int hPoints;
    @Min(value = 0, message="Defense should not be less than 0!")
    protected int defense;

    public Player(String name, int attack, int hPoints, int defense)
    {
        this.setName(name);
        this.setAttack(attack);
        this.sethPoints(hPoints);
        this.setDefense(defense);
    }


    @NotNull
    public String getName()
    {
        return name;
    }

    public void setName(@NotNull String name)
    {
        this.name = name;
    }

    public int getAttack()
    {
        return attack;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public int gethPoints()
    {
        return hPoints;
    }

    public void sethPoints(int hPoints)
    {
        this.hPoints = hPoints;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }
    public void attack(Player player)
    {
        int playerDefense = player.getDefense();
        int playerPoints = player.gethPoints();
        int randNumber = ThreadLocalRandom.current().nextInt(0,10);
        if (this.getAttack() > playerDefense)
        {
            int difference = playerPoints - (this.attack - playerDefense);
            player.sethPoints(difference);
        }
        else
            if (randNumber <= 2)
            {
                player.sethPoints(playerPoints - this.attack);
            }
    }

    public boolean battle(Player player)
    {
        boolean results = false;
        int playerPoints = player.gethPoints();
        int heroPoints  = this.gethPoints();
        while(playerPoints > 0 && heroPoints > 0)
        {
            this.attack(player);
            player.attack(this);
            playerPoints = player.gethPoints();
            heroPoints  = this.gethPoints();
        }
        results = playerPoints > 0;
        return results;
    }
}
