package default_methods.framework;

/**
 * Created by lukasz on 12.09.17.
 */
public interface Resizable {
    // API v1
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);

    //API v2 - no need t implement in classes because is default
    default void setRelativeSize(int wFactor, int hFactor){
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    };
}
