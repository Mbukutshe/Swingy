package Swingy.Controller;

import Swingy.Model.Coordinates;
import Swingy.Model.RPGGame;
import Swingy.View.Game;

public class Field {
    protected Game field;
    protected RPGGame game;
    protected Coordinates coordinates;

    public void PrintField()
    {
        field.printOutMap(game.returnField(), game.getCoordinates());
    }

    public void Move(int option)
    {
        int x_coordinate = game.getCoordinates().getXCoord();
        int y_coordinate = game.getCoordinates().getYCoord();
        coordinates.setXCoord(x_coordinate);
        coordinates.setYCoord(y_coordinate);
        switch (option)
        {
            case 1:
                y_coordinate = y_coordinate - 1;
                break;
            case 2:
                x_coordinate = x_coordinate + 1;
                break;
            case 3:
                y_coordinate = y_coordinate + 1;
                break;
            case 4:
                x_coordinate = x_coordinate - 1;
                break;
        }
        this.involveInBattle(x_coordinate, y_coordinate);
    }

    public void involveInBattle(int x_coordinate, int y_coordinate)
    {
        game.getCoordinates().setXCoord(x_coordinate);
        game.getCoordinates().setYCoord(y_coordinate);
    }

    public void change()
    {
        this.field.switchGame();
    }

    public Field(Game field)
    {
        this.field = field;
        this.coordinates = new Coordinates(0,0);
    }
}
