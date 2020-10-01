package Swingy.View;

import Swingy.Controller.Begin;

import javax.swing.*;

public class StartGUI extends JPanel {

    private JButton createHero= new JButton("Create Hero");
    private JButton selectHero = new JButton("Select Hero");
    private JButton switchView = new JButton("Switch to console");

    public  Begin c;

    public void start() {
        newUI();
    }

    public void newUI()
    {

    }
}
