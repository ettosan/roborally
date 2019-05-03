package inf112.skeleton.app.robot;

import inf112.skeleton.app.board.Direction;

import java.awt.image.BufferedImage;
import java.util.Objects;


/**
 *
 * Creates a robot character to assign to a player.
 */
public class Robot implements IRobot{
    private Direction dir;
    private final int id;
    private int[] coordinate;
    private int[] backup;

    /**
     * Creates a robot using the coordinates and id supplied
     *
     * @param id the id of the robot
     * @param coordinate the coordinates the robot is on
     */
    public Robot(int id, int[] coordinate) {
        dir = Direction.NORTH; //Is set to north since all of our maps have the start points south & a robot should start facing the main board
        this.id = id;
        this.coordinate = coordinate;
        backup = coordinate;
    }

    @Override
    public void rotate(Boolean rotateDirection) {
        if(rotateDirection)
            dir = dir.next();
        else
            dir = dir.previous();
    }

    @Override
    public Direction getDir() {
        return dir;
    }

    @Override
    public void setDir(Direction dirIn) {
        dir = dirIn;
    }

    @Override
    public int getID(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return id == robot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int[] getBackup() {
        return backup;
    }

    @Override
    public void setBackup(int[] backup) {
        this.backup = backup;
    }

    @Override
    public int[] getPosition() {
        return coordinate;
    }

    @Override
    public void setPosition(int[] position) {
        coordinate = position;
    }
}