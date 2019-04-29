package inf112.skeleton.app.board;

public class Laser implements ILaser {
    private final int damage;
    private final boolean isVertical;
    private int[] position;

    public Laser(int damage, boolean isVertical, int x, int y) {
        position = new int[2];
        position[0] = x;
        position[1] = y;
        this.damage = damage;
        this.isVertical = isVertical;
    }

    public Laser(int damage, String direction, int x, int y) {
        position = new int[2];
        position[0] = x;
        position[1] = y;
        if(direction.equals("vertical"))
            isVertical = true;
        else if(direction.equals("horizontal"))
            isVertical = false;
        else
            throw new IllegalArgumentException("Direction has to be either horizontal or vertical");
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public boolean isVertical() {
        return isVertical;
    }

    @Override
    public int[] getPosition(){ return position; }
}
