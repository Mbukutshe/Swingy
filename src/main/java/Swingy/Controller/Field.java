package Swingy.Controller;

import Swingy.JDBC.db;
import Swingy.Model.Coordinates;
import Swingy.Model.Enemy;
import Swingy.Model.Hero;
import Swingy.Model.RPGGame;
import Swingy.View.Game;

import java.util.Random;

public class Field
{
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
        if (x_coordinate < 0 ||
                y_coordinate < 0 ||
                x_coordinate >= game.returnFieldSize() ||
                y_coordinate >= game.returnFieldSize()) {
            win();
            return;
        }
        this.involveInBattle(x_coordinate, y_coordinate);
    }

    public void involveInBattle(int x_coordinate, int y_coordinate)
    {
        game.getCoordinates().setXCoord(x_coordinate);
        game.getCoordinates().setYCoord(y_coordinate);
        if (game.returnField()[x_coordinate][y_coordinate]) {
            attack();
        }

        if (game.getHero().gethPoints() > 0)
            this.field.updateGame(game);
    }

    private void win() {
        this.field.printMessage("You won! and got additional " + game.returnFieldSize() * 100 + "xp!");
        addExperience(game.returnFieldSize() * 100);
        updateDataBase();
        this.field.finishGame();
    }

    private void addExperience(int xp) {
        int level = game.getHero().getLevel();
        game.getHero().increaseExp(xp);
        if (level != game.getHero().getLevel())
            this.field.printMessage("Increase!!!\nHP, attack and defense were increased!");
    }

    private void updateDataBase() {
        Hero hero = game.getHero();
        db.update(hero);
    }

    public void onAttack()
    {
        Enemy enemy = game.returnEnemy();
        int xp = game.Result(enemy);

        if (xp >= 0) {
            this.field.printMessage("You defeated the Enemy, and got " + xp + "xp.");
            addExperience(xp);
            game.returnField()[game.getCoordinates().getYCoord()][game.getCoordinates().getXCoord()] = false;
            updateDataBase();
        }
        else
        {
            this.field.printMessage("Game over!!!!You Lost.");
            this.field.finishGame();
        }
    }

    public void attack()
    {
        this.field.getEnemyInput();
    }
    public void change()
    {
        this.field.switchGame();
    }

    public void run()
    {
        if (new Random().nextBoolean()) {
            this.field.printMessage("moved to previous position!");
            game.getCoordinates().setXCoord(coordinates.getXCoord());
            game.getCoordinates().setYCoord(coordinates.getYCoord());
        } else {
            this.field.printMessage("You fought");
            onAttack();
        }
    }

    public void startGame()
    {
        this.field.updateGame(game);
    }

    public Field(Game field)
    {
        this.field = field;
        game = RPGGame.getContext();
        this.coordinates = new Coordinates(0,0);
    }
}
