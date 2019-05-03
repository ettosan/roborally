package inf112.skeleton.app.graphics;

public class MessageGFX {
    private String message;
    private int[] position;
    private boolean isVisible;
    private int duration;
    private float scale;

    public MessageGFX(String message, int[] position, boolean isVisible, float scale) {
        this(message, position, isVisible, scale, -1);
    }

    public MessageGFX(String message, int[] position, boolean isVisible, float scale, int duration) {
        this.message = message;
        this.position = position;
        this.isVisible = isVisible;
        this.scale = scale;
        this.duration = duration;
    }

    public boolean hasDuration() {
        return duration != -1;
    }

    /**
     * Decreases the duration by one time-unit and checks if the duration has expired (== 0)
     * @return true if duration = 0, false if != 0
     */
    public boolean decreaseDuration() {
        if(duration > 0);
            duration -= 1;
        return duration == 0;
    }

    public String getMessage() {
        return message;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getDuration() {
        return duration;
    }

    public float getScale() {
        return scale;
    }
}
