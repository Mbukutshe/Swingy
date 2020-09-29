package Swingy.View;

import Swingy.Controller.CreateHero;
import Swingy.Main;

import java.util.Scanner;

public class CreateHeroConsole {

    public CreateHero c;
    private  Scanner scanner;
    public void begin()
    {
        c = new CreateHero();
        scanner = Main.returnScanner();

        System.out.println("Enter hero name and class.\n" +
                "Enter name:");
        String name = scanner.nextLine();
        System.out.println("Classes: attack  defense   hp\n" +
                "Warrior    40      20      100\n" +
                "Soldier    30      15      90\n" +
                "Hunter     25      20      110\n" +
                "Enter class name: ");
        String heroClass = scanner.nextLine();

        System.out.println("1:CREATE - hero with previously entered Name and Class.\n" +
                "Options (1):");
        while (scanner.hasNext())
        {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("1"))
            {
                c.create(name, heroClass);
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}
