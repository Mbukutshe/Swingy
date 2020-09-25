package Model;

public abstract class Extras {
    private String name;
    private int points;

    public Extras(String name, int points){
        this.setName(name);
        this.setPoints(points);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
