package inf112.skeleton.app.board;

public class Flag implements IFlag{
    private int flagId;
    private int[] coordinates = new int[2];

    public Flag (int flagId, int x, int y) {
        this.flagId = flagId;
        coordinates[0] = x;
        coordinates[1] = y;
    }

    @Override
    public int[] getPosition() {
        return coordinates;
    }

    @Override
    public int getFlagId() {
        return flagId;
    }


}
