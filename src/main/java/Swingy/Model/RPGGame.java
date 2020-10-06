package Swingy.Model;

import java.util.concurrent.ThreadLocalRandom;

public class RPGGame {
    private static RPGGame context = null;

    protected Hero hero;
    protected Coordinates coordinates;
    protected int mapSize;
    protected boolean[][] map;

    public void beginRPGGame(Hero hero)
    {
        this.hero = hero;
        createGame();
    }

    public void createGame()
    {
        createField();
        placeEnemies();
        centreHero();
    }

    public void createField()
    {
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    public Enemy returnEnemy()
    {
        int aValue = Constants.returnAttackValue(hero);
        int dValue = Constants.returnDefenseValue(hero);
        int hValue = Constants.returnHPoints(hero);

        if (aValue < 0)
            aValue = -1 * aValue;
        if(dValue< 0)
            dValue = -1 * dValue;
        if(hValue < 0)
            hValue = -1 * hValue;

        Extras extras = createExtras();
        return new Enemy("Enemy", aValue, hValue, dValue, extras);
    }

    public Extras createExtras() {
        int value = ThreadLocalRandom.current().nextInt(0, 10);

        Extras extras = null;
        if (value == 0) {
            extras = new Weapon("Sword", Constants.returnWeaponValue(hero));
        }
        else if (value == 1) {
            extras = new Helm("Pot", Constants.returnHelmValue(hero));
        }
        else if (value == 2)
            extras = new Armor("Shield", Constants.returnArmorValue(hero));
        return extras;
    }

    public static RPGGame getContext()
    {
        if (context == null)
        {
            context = new RPGGame();
        }
        return context;
    }

    public void placeEnemies()
    {
        int value = 0;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++)
        {
            for (int j = 0; j < mapSize; j++)
            {
                value = ThreadLocalRandom.current().nextInt(0, 100);
                if ((level + 1) * 10 >= value)
                {
                    map[i][j] = true;
                }
            }
        }
    }

    public void centreHero()
    {
        coordinates = new Coordinates(mapSize / 2, mapSize / 2);
        map[coordinates.getYCoord()][coordinates.getXCoord()] = false;
    }

    public Hero getHero()
    {
        return hero;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public boolean[][] returnField()
    {
        return map;
    }

    public int returnFieldSize()
    {
        return mapSize;
    }

    public void setField(boolean[][] field)
    {
        this.map = field;
    }

    public Coordinates getCoordinates()
    {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates)
    {
       this.coordinates = coordinates;
    }

}
