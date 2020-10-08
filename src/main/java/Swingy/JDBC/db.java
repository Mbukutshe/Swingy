package Swingy.JDBC;

import java.sql.*;
public class db
{
    private static Connection connection;
    private Statement statement;
    private String username = "root";
    private  String password = "";

    public db()
    {
        String url = "jdbc:sqlite:RPGGAME.db";
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        createTable();
    }

    public int putValues(String name, String className, int level, int exp, int attack, int hp, int defense)
    {
        if (this.getConnection() != null)
        {
            String query = "INSERT INTO `heroes` (`name`, `className`, `level`, `exp`, `attack`, `hp`, `defense`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) ";
            try
            {
                statement = connection.createStatement();
                statement.execute(query);
            } catch (SQLException e)
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
        return 1;
    }

    public void createTable()
    {
        if (this.getConnection() != null)
        {
            String url = "jdbc:sqlite:RPGGAME.db";
            String sql = "CREATE TABLE IF NOT EXISTS `heroes` (" +
                    "`id` INT PRIMARY KEY NOT NULL," +
                    "`name` TEXT NOT NULL," +
                    "`className` TEXT NOT NULL," +
                    "`level` INT NOT NULL," +
                    "`exp` INT NOT NULL," +
                    "`attack` INT NOT NULL," +
                    "`hp` INT NOT NULL," +
                    "`defense` INT NOT NULL)";
            try
            {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
                statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            }
            catch (ClassNotFoundException | SQLException e)
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Table created successfully\n");
        }
    }

    public void properConnection()
    {
        String url = "jdbc:mysql://localhost:3306/RPGGAME";
        try
        {
            connection = DriverManager.getConnection(url, this.username, this.password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return;
        }
    }

    public Connection getConnection()
    {
        return this.connection;
    }

    public void closeConnection()
    {
        if (this.connection != null)
        {
            try
            {
                this.connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return;
            }
        }
    }
}
