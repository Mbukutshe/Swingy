package Swingy.Controller;

import Swingy.Model.Hero;
import Swingy.Model.RPGGame;
import Swingy.View.Choose;
import Swingy.JDBC.db;
import java.util.ArrayList;

public class Pick
{
    Choose choose;
    private RPGGame field;
    public Pick(Choose choose)
    {
        this.choose = choose;
        this.field = RPGGame.getContext();
    }
    public String[] createdHeroes()
    {
        ArrayList<String> heroes = db.selectHeroes();
        String [] heroeList = new String[heroes.size()];
        heroeList = heroes.toArray(heroeList);
        return heroeList;
    }

    public void printOut(String[] heroeList)
    {
        if(heroeList.length < 1)
        {
            choose.error("No Created Heroes!!");
        }
        else
        {
            for (String hero : heroeList)
            {
                choose.printMessage(hero);
            }
        }
    }

    public boolean validateNumber(String option)
    {
        try
        {
            int num = Integer.parseInt(option);
            if(num <= 0 || num > createdHeroes().length)
            {
                return  false;
            }
        }
        catch (Exception e)
        {
            return  false;
        }
        return true;
    }

    public void createHero()
    {
        this.choose.openHero();
    }

    public void selectByID(int id)
    {
        Hero hero = db.chooseHero(id);
        this.choose.printMessage(hero.printOut());
        this.field.beginRPGGame(hero);
        this.choose.beginGame();
    }
}
