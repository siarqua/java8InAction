package default_methods.framework;

/**
 * Created by lukasz on 12.09.17.
 */
public interface Rotable {
    void setRotationAngle(int angleInDegrees);
    int getRotationAngle();
    default void rotateBy(int angleInDegrees){
        setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
    }
}
