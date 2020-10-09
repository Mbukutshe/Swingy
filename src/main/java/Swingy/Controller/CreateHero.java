package Swingy.Controller;

import Swingy.JDBC.db;
import Swingy.Model.Hero;
import Swingy.Model.HeroCreator;
import Swingy.Model.RPGGame;
import Swingy.Validation.HeroException;
import Swingy.View.Create;

public class CreateHero {

    protected RPGGame game;
    protected Create create;

    public void create(String name, String heroClass)
    {
        Hero hero;
        try
        {
            hero = HeroCreator.createHero(name, heroClass);
            hero.validate();
        } catch (IllegalArgumentException | HeroException e) {
            this.create.error(e.getMessage());
            this.create.readInput();
            return;
        }
        int id = db.putValues(hero.getName(),
                hero.getHeroClass(),
                hero.getLevel(),
                hero.getExp(),
                hero.getAttack(),
                hero.gethPoints(),
                hero.getDefense());
        hero.setHeroId(id);
        game.beginRPGGame(hero);
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
