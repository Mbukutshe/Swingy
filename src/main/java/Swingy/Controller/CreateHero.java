package Swingy.Controller;

import Swingy.Model.Hero;
import Swingy.Model.HeroCreator;
import Swingy.Model.RPGGame;
import Swingy.View.Create;
import Swingy.View.CreateHeroConsole;

public class CreateHero {

    protected RPGGame game;
    protected Create create;

    public void create(String name, String heroClass)
    {
        Hero hero;
        try {
            hero = HeroCreator.createHero(name, heroClass);
            //here!! will call validation method for hero
        } catch (IllegalArgumentException e) {
            this.create.error(e.getMessage());
            this.create.readInput();
            return;
        }

//        int id = DataBase.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
//        hero.setId(id);
//        game.initGame(hero);
        openGame();
    }

    public void openGame()
    {
        this.create.beginGame();
    }

    public CreateHero(Create create)
    {
        this.game = RPGGame.getContext();
        this.create = create;
    }
}
