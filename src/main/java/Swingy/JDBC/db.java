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
        String url = "jdbc:mysql://localhost:3306";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, this.username, this.password);
            this.createDB();
            this.properConnection();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createDB()
    {
        if (this.getConnection() != null)
        {
            String query = "CREATE DATABASE `RPGGAME` IF NOT EXISTS";
            try
            {
                statement = connection.createStatement();
                statement.execute(query);
            } catch (SQLException e)
            {
                e.printStackTrace();
                return;
            }
        }
    }

    public void createTable()
    {
        if (this.getConnection() != null)
        {
            String query = "CREATE DATABASE `RPGGAME` IF NOT EXISTS";
            try
            {
                statement = connection.createStatement();
                statement.execute(query);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return;
            }
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
