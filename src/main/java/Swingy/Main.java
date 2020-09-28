package Swingy;
import java.util.Scanner;
public class Main {
    private static Scanner scanner;
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Usage: pass console | gui as an argument");
            System.exit(1);
        }
        if ((!args[0].equals("console") && !args[0].equals("gui")))
        {
            System.out.println("Usage: pass console | gui as an argument");
            System.exit(1);
        }

    }

    public static Scanner returnScanner()
    {
        if (scanner == null)
        {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeObjects()
    {
        if (scanner != null)
        {
            scanner.close();
        }
    }
}
