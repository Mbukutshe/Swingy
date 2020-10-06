package Swingy.View;

import Swingy.Model.Coordinates;
import Swingy.Model.RPGGame;

public abstract class Game
{
    public abstract void startGame();

    public abstract void printOutMap(boolean[][] field, Coordinates coordinates);

    public abstract void updateGame(RPGGame game);

    public abstract void finishGame();

    public abstract void pintMessage(String message);

    public abstract void getEnemyInput();

    public abstract boolean replaceExtras(String replaceMessage);

    public abstract void switchGame();
}
