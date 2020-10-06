package Swingy.View;

import Swingy.Controller.CreateHero;
import Swingy.Main;

import java.util.Scanner;

public class CreateHeroConsole extends Create{

    public CreateHero c;
    private  Scanner scanner;

    @Override
    public void readInput()
    {
        c = new CreateHero(this);
        scanner = Main.returnScanner();

        System.out.println("Enter hero name and class.\n" +
                "Enter name:");
        String name = (scanner.nextLine()).trim();
        System.out.println("Classes: attack  defense   hp\n" +
                "Warrior    40      20      100\n" +
                "Soldier    30      15      90\n" +
                "Hunter     25      20      110\n" +
                "Enter class name: ");
        String heroClass = (scanner.nextLine()).trim();
        c.create(name, heroClass);
    }

    @Override
    public void beginGame()
    {
        new GameConsole().init();
    }

    @Override
    public void error(String message) {
        System.out.println("error:" + message);
    }

}
