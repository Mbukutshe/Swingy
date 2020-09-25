package Model;

public class HeroCreator {

    public static NewHero newHero(String name){
        NewHero hero = new NewHero();
        hero.setLevel(0);
        hero.setExp(0);
        hero.setName(name);
        return  hero;
    }

    public static Hero newCoward(String name){
        NewHero hero = newHero(name);
        hero.setHeroClass("Coward");
        hero.setDefense(15);
        hero.setAttack(10);
        hero.sethPoints(40);
        return hero.hero();
    }

    public static Hero newSoldier(String name){
        NewHero hero = newHero(name);
        hero.setHeroClass("Soldier");
        hero.setDefense(40);
        hero.setAttack(60);
        hero.sethPoints(100);
        return hero.hero();
    }

    public static Hero newWarrior(String name){
        NewHero hero = newHero(name);
        hero.setHeroClass("Warrior");
        hero.setDefense(30);
        hero.setAttack(50);
        hero.sethPoints(90);
        return hero.hero();
    }
}
