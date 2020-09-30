package Swingy.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Constants
{
    public static int returnWeaponValue(Hero hero)
    {
        return ThreadLocalRandom.current().nextInt(1, 5 * (hero.getLevel() + 1));
    }

    public static int returnHelmValue(Hero hero)
    {
        return ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1));
    }

    public static int returnArmorValue(Hero hero)
    {
        return ThreadLocalRandom.current().nextInt(1, 4 * (hero.getLevel() + 1));
    }

    public static int returnAttackValue(Hero hero)
    {
        return ThreadLocalRandom.current().nextInt(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
    }

    public static int returnDefenseValue(Hero hero)
    {
        return ThreadLocalRandom.current().nextInt(hero.getDefense() - 20, hero.getDefense() + 2 + hero.getLevel());
    }

    public static int returnHPoints(Hero hero)
    {
        return ThreadLocalRandom.current().nextInt(hero.gethPoints() - 50, hero.gethPoints() + 20 + hero.getLevel());
    }
}
