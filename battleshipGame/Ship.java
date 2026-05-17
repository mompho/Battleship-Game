package battleshipGame;

public class Ship
{
    private int size;
    private char letter;
    private char direction;
    private Coordinate coord;

    public Ship() {}

    public Ship(char let, char dir, Coordinate crd)
    {
        letter    = let;
        direction = dir;
        coord     = crd;

        if      (let == 'A')               size = 5;
        else if (let == 'B')               size = 4;
        else if (let == 'S' || let == 'D') size = 3;
        else                               size = 2;
    }

    public int getSize()         { return size; }
    public char getLetter()      { return letter; }
    public char getDirection()   { return direction; }
    public Coordinate getCoord() { return coord; }
}
