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
        System.out.println();
        System.out.println("CREATE - to create hero");
        System.out.println("SELECT - to select already created hero");
        System.out.println("SWITCH - to switch to GUI view");
        System.out.println("Commands (CREATE, SELECT, SWITCH):");
        while (scanner.hasNext())
        {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("create"))
            {
                c.createHero();
                break;
            }
            else if (command.equalsIgnoreCase("select"))
            {
                c.selectHero();
                break;
            }
            else if (command.equalsIgnoreCase("switch"))
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
