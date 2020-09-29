package Swingy.Controller;

import Swingy.Model.Hero;
import Swingy.Model.HeroCreator;
import Swingy.View.CreateHeroConsole;

public class CreateHero {

    public void create(String name, String heroClass) {
        Hero hero;
        try {
            hero = HeroCreator.createHero(name, heroClass);
            //here!! will call validation method for hero
        } catch (IllegalArgumentException e) {
            System.out.println("error:" + e.getMessage());
            new CreateHeroConsole().begin();
            return;
        }

//        int id = DataBase.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
//        hero.setId(id);
//        game.initGame(hero);
//        view.openGame();
    }
}
