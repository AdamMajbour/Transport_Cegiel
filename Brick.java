package sample;

public class Brick
{
    private int weight;

    public Brick(int weight)
    {
        this.weight = weight;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public String toString() {
        return "[" + weight + "]";
    }
}