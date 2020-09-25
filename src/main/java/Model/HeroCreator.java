package Model;

public class HeroCreator {

    public static NewHero newHero(String name){
        NewHero hero = new NewHero();
        hero.setName((name));
        return  hero;
    }
}
