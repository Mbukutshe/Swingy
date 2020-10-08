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
        int id = 0;
        if (this.getConnection() != null)
        {
            String query = "INSERT INTO `heroes` (`name`, `className`, `level`, `exp`, `attack`, `hp`, `defense`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) ";
            try
            {
                PreparedStatement pre_stmt = connection.prepareStatement(query);
                pre_stmt.setString(1, name);
                pre_stmt.setString(2, className);
                pre_stmt.setInt(3, level);
                pre_stmt.setInt(4, exp);
                pre_stmt.setInt(5, attack);
                pre_stmt.setInt(6, hp);
                pre_stmt.setInt(7, defense);
                pre_stmt.executeUpdate();

                query = "SELECT seq FROM sqlite_sequence WHERE `name` = \"heroes\"";
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet.next())
                {
                    id = resultSet.getInt("seq");
                }
            } catch (SQLException e)
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
        return id;
    }

    public void createTable()
    {
        if (this.getConnection() != null)
        {
            String url = "jdbc:sqlite:RPGGAME.db";
            String sql = "CREATE TABLE IF NOT EXISTS `heroes` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
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
