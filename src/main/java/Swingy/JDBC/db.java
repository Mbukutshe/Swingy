package Swingy.JDBC;

import Swingy.Model.Hero;
import Swingy.Model.NewHero;

import java.sql.*;
import java.util.ArrayList;

public class db
{
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement pre_stmt = null;
    private static  ResultSet resultSet = null;

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

    public static int putValues(String name, String className, int level, int exp, int attack, int hp, int defense)
    {
        int id = 0;
        if (properConnection() != null)
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
                statement = properConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                System.out.println(selectHeroes().toString());
                if(resultSet.next())
                {
                    id = resultSet.getInt("seq");
                }
                statement.close();
                connection.close();
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
                statement = properConnection().createStatement();
                statement.executeUpdate(sql);
                connection.close();
            }
            catch (SQLException e)
            {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
    }

    public static ArrayList<String> selectHeroes() {
        String sqlQuery = "SELECT * FROM heroes";
        ArrayList<String> arrayList = new ArrayList<>();

        try (Statement stmt = properConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            arrayList.add("ID\tName\tClass\t\tLevel\t\tExp\t\tAttack\t\tHp\t\tDefense");
            for (int i = 1; rs.next(); i++)
            {
                arrayList.add(String.format("%d.\t%s\t%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d ", i,
                        rs.getString("name"),
                        rs.getString("className"),
                        rs.getInt("level"),
                        rs.getInt("exp"),
                        rs.getInt("attack"),
                        rs.getInt("hp"),
                        rs.getInt("defense")));
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    public static Connection properConnection()
    {
        if (connection != null)
        {
            String url = "jdbc:sqlite:RPGGAME.db";
            try
            {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
            }
            catch (SQLException | ClassNotFoundException e)
            {
                System.out.println("error: " + e.getMessage());
            }
        }

        return connection;
    }

    public static void update(Hero hero)
    {
        String query = "UPDATE heroes SET level = ?, exp = ?, attack = ?, hp = ?, defense = ? " +
                "WHERE id = ?";
        try
        {
            PreparedStatement pre_stmt = properConnection().prepareStatement(query);
            pre_stmt.setInt(1, hero.getLevel());
            pre_stmt.setInt(2, hero.getExp());
            pre_stmt.setInt(3, hero.getAttack());
            pre_stmt.setInt(4, hero.gethPoints());
            pre_stmt.setInt(5, hero.getDefense());
            pre_stmt.setInt(6, hero.getHeroId());
            pre_stmt.executeUpdate();
            System.out.println("Supposed to update the hero Details." +
                    "\nLevel : " + hero.getLevel() + "\n" +
                    "Experience : " + hero.getExp() + "\n" +
                    "Attack : " + hero.getAttack() + "\n" +
                    "HP : " + hero.gethPoints() + "\n" +
                    "Defense : " + hero.getDefense() + "\n" +
                    "Id : " + hero.getHeroId());
            pre_stmt.close();
            connection.close();
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

    public static void closeConnection()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static  Hero chooseHero(int id)
    {
        Hero hero = null;
        NewHero newHero = null;
        String query = "SELECT * FROM heroes WHERE id = ?";
        try
        {

            pre_stmt = properConnection().prepareStatement(query);
            pre_stmt.setInt(1, id);
            resultSet = pre_stmt.executeQuery();
            if(resultSet.next()) {
                newHero = new NewHero();
                newHero.setHeroId(resultSet.getInt("id"));
                newHero.setName(resultSet.getString("name"));
                newHero.setHeroClass(resultSet.getString("className"));
                newHero.setLevel(resultSet.getInt("level"));
                newHero.setExp(resultSet.getInt("exp"));
                newHero.setAttack(resultSet.getInt("attack"));
                newHero.sethPoints(resultSet.getInt("hp"));
                newHero.setDefense((resultSet.getInt("defense")));
            }
            pre_stmt.close();
            connection.close();
            hero = newHero.hero();
        }
        catch (SQLException e)
        {
            System.out.println("error: "+ e.getMessage());
        }
        return hero;
    }
}
