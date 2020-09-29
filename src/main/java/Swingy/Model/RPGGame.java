package Swingy.Model;

public class RPGGame {
    private static RPGGame context = null;

    protected Hero hero;
    protected Coordinates coordinates;
    protected int mapSize;
    protected boolean[][] map;

    public void beginRPGGame(Hero hero) {
        this.hero = hero;
        createGame();
    }

    public void createGame()
    {
        createMap();
        centreHero();
    }

    public void createMap()
    {
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    public static RPGGame getContext()
    {
        if (context == null)
        {
            context = new RPGGame();
        }
        return context;
    }

    public void centreHero()
    {
        coordinates = new Coordinates(mapSize / 2, mapSize / 2);
        map[coordinates.getYCoord()][coordinates.getXCoord()] = false;
    }

}
