package Swingy.View;

import Swingy.Controller.Begin;
import Swingy.Main;

import java.util.Scanner;

public class StartConsole {

    public Begin c;
    public void start() {
        c = new Begin();
        System.out.println("enter available commands to play.");

        Scanner scanner = Main.returnScanner();
        System.out.println("\n1:CREATE - hero\n" +
                "2:SELECT - created hero\n" +
                "3:SWITCH - GUI View\n" +
                "Options (1, 2, 3):");
        while (scanner.hasNext())
        {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("1"))
            {
                c.createHero();
                break;
            }
            else if (command.equalsIgnoreCase("2"))
            {
                c.selectHero();
                break;
            }
            else if (command.equalsIgnoreCase("3"))
            {
                c.switchMode();
                break;
            }
            else
            {
                System.out.println("Unknown command");
            }
        }
    }
}
