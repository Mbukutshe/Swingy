package Swingy.Model;

public class HeroCreator {

    public static Hero createHero(String name, String hClass) {
        if (hClass.equalsIgnoreCase("Warrior"))
            return newWarrior(name);
        else if (hClass.equalsIgnoreCase("Soldier"))
                return newSoldier(name);
            else if (hClass.equalsIgnoreCase("Hunter"))
                    return newHunter(name);
                else
                    throw new IllegalArgumentException("Invalid Hero Class");
    }

    public static NewHero newHero(String name){
        NewHero hero = new NewHero();
        hero.setLevel(1);
        hero.setExp(500);
        hero.setName(name);
        return  hero;
    }

    public static Hero newHunter(String name){
        NewHero hero = newHero(name);
        hero.setHeroClass("Hunter");
        hero.setDefense(15);
        hero.setAttack(10);
        hero.sethPoints(40);
        return hero.hero();
    }

    public static Hero newSoldier(String name){
        NewHero hero = newHero(name);
        hero.setHeroClass("Soldier");
        hero.setDefense(30);
        hero.setAttack(50);
        hero.sethPoints(90);
        return hero.hero();
    }

    public static Hero newWarrior(String name){
        NewHero hero = newHero(name);
        hero.setHeroClass("Warrior");
        hero.setDefense(40);
        hero.setAttack(60);
        hero.sethPoints(100);
        return hero.hero();
    }
}
