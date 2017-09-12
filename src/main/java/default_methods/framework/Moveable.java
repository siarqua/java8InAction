package default_methods.framework;

/**
 * Created by lukasz on 12.09.17.
 */
public interface Moveable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    default void moveHorizontally(int distance) {
        setX(getX() + distance);
    }
    default void moveVertically(int distance) {
        setY(getY() + distance);
    }
}
