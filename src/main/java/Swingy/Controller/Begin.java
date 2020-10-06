package Swingy.Controller;

import Swingy.View.*;

public class Begin {

    protected Entry entry;

    public void createHero()
    {
        this.entry.CreateHero();
    }

    public Begin(Entry entry)
    {
        this.entry = entry;
    }

    public void selectHero()
    {
        this.entry.chooseHero();
    }

    public void switchMode()
    {
        this.entry.switchInterface();
    }

}
