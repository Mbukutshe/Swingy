package Swingy.JDBC;

import Swingy.Model.Hero;

import java.sql.*;
import java.util.ArrayList;

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
                System.out.println(selectHeroes().toString());
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

    public static ArrayList<String> selectHeroes() {
        String sqlQuery = "SELECT * FROM heroes";
        ArrayList<String> arrayList = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            arrayList.add("ID\tName\tClass\tLevel\tExp\tAttack\tHp\tDefense");
            for (int i = 1; rs.next(); i++)
            {
                arrayList.add(String.format("%d.\t%s\t(%s)\t%d\t%d\t%d\t%d\t%d ", i,
                        rs.getString("name"),
                        rs.getString("className"),
                        rs.getInt("level"),
                        rs.getInt("exp"),
                        rs.getInt("attack"),
                        rs.getInt("hp"),
                        rs.getInt("defense")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
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

    public static void update(Hero hero)
    {
        String query = "UPDATE heroes SET level = ?, exp = ?, attack = ?, hp = ?, defense = ? , " +
                "WHERE id = ?";
        try
        {
            PreparedStatement pre_stmt = connection.prepareStatement(query);
            pre_stmt.setInt(1, hero.getLevel());
            pre_stmt.setInt(2, hero.getExp());
            pre_stmt.setInt(3, hero.getAttack());
            pre_stmt.setInt(4, hero.gethPoints());
            pre_stmt.setInt(5, hero.getDefense());
            pre_stmt.setInt(6, hero.getHeroId());
            pre_stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("error: "+e.getMessage());
            System.exit(0);
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
