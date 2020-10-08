package Swingy.View;

import Swingy.Controller.Field;
import Swingy.Main;
import Swingy.Model.Coordinates;
import Swingy.Model.RPGGame;

import java.util.Scanner;

public class GameConsole extends Game{
    Field c;

    public void init()
    {
        c = new Field(this);
        c.startGame();
    }

    @Override
    public void startGame()
    {
        Scanner scanner = Main.returnScanner();

        System.out.println("1: move to NORTH,\n 2: move to EAST,\n 3: move to SOUTH,\n 4: move to WEST");
        System.out.println("5:SWITCH - to GUI");
        System.out.println("Options (1, 2, 3, 4, 5):");
        while (scanner.hasNext())
        {
            String v = (scanner.nextLine()).trim();

            if("map".equalsIgnoreCase(v))
            {
                c.PrintField();
                break;
            }
            else if("1".equalsIgnoreCase(v) || "2".equalsIgnoreCase(v) || "3".equalsIgnoreCase(v) || "4".equalsIgnoreCase(v))
            {
                c.Move(Integer.parseInt(v));
                break;
            }
            else if("switch".equalsIgnoreCase(v))
            {
                c.change();
                break;
            }
            else
            {
                System.out.println("Unknown command");
            }
        }

    }

    @Override
    public void printOutMap(boolean[][] field, Coordinates coordinates)
    {
        System.out.printf("FIELD %dx%d\n", field.length, field.length);
//        System.out.println();
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[i].length; j++)
            {
                if (coordinates.getXCoord() == j && coordinates.getYCoord() == i)
                {
                    System.out.print("H ");
                }
                else if (field[i][j])
                {
                    System.out.print("* ");
                }
                else
                {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }

    }

    @Override
    public void updateGame(RPGGame game)
    {
        System.out.println("************** Hero Details ************");
        System.out.println(game.getHero().printOut() +
                "\tPosition: " + "(" + game.getCoordinates().getXCoord() +
                "," + game.getCoordinates().getYCoord() + ")");
        System.out.println("**********************");
        startGame();
    }

    @Override
    public void finishGame()
    {
        System.out.println("Game Finished!");
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void getEnemyInput()
    {
        Scanner scanner = Main.returnScanner();

        System.out.println("\nYou encountered an Enemy\n1:FIGHT - to fight with Enemy\n2:RUN - to run, 50% chance to move to the previous position");
        System.out.println("options (1, 2):");
        while (scanner.hasNext())
        {
            String v = scanner.nextLine();

            if("1".equalsIgnoreCase(v))
            {
                c.onAttack();
                break;
            }
            else if("2".equalsIgnoreCase(v))
            {
                c.run();
                break;
            }
            else
            {
                System.out.println("Invalid option");
            }
        }
    }

    @Override
    public boolean replaceExtras(String replaceMessage)
    {
        return false;
    }

    @Override
    public void switchGame()
    {

    }
}
