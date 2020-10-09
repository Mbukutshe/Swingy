package Swingy.View;

import  Swingy.Controller.Pick;
import Swingy.Main;

import java.util.Scanner;

public class ChooseConsole implements Choose{

    Pick c;

    @Override
    public void entry() {
        c = new Pick(this);
        begin();
    }

    @Override
    public void begin()
    {
        Scanner scanner = Main.returnScanner();
        printMessage("\t\t\t\t\t************************************\n\t\t\t\t\t\tAlready Created Heroes\n\t\t\t\t\t************************************");
        c.printOut(c.createdHeroes());
        printMessage("Aavilable Commands:\n" +
                "1.CREATE - Create Hero\n" +
                "2.To Choose - Enter heroe's ID");
        while (scanner.hasNext())
        {
            String option = scanner.nextLine();
            if("create".equalsIgnoreCase(option))
            {
                c.createHero();
                break;
            }
            else
                if(c.validateNumber(option))
                {
                    c.selectByID(Integer.parseInt(option));
                }
                else
                {
                    error("Invalid command!!");
                }
        }
    }

    @Override
    public void error(String error)
    {
        System.out.println(error);
        begin();
    }

    @Override
    public void printMessage(String message)
    {
        System.out.println(message);
    }

    @Override
    public void beginGame()
    {
        new GameConsole().init();
    }

    @Override
    public void openHero() {
        new CreateHeroConsole().readInput();
    }
}
