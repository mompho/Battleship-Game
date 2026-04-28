package battleshipGame;

public class Coordinate
{
    private int xCor;
    private int yCor;

    public Coordinate()
    {
        xCor = 1;
        yCor = 1;
    }
    public Coordinate(int x, int y)
    {
        xCor = x;
        yCor = y;
    }
    public int getXCor()
    {
        return xCor;
    }
    public int getYCor()
    {
        return yCor;
    }
    public String toString()
    {
        return "("+getXCor()+ ", "+getYCor() + ")";
    }
    public static void main(String args[])
    {

    }
}