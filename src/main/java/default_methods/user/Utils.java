package default_methods.user;

import default_methods.framework.Resizable;

import java.util.List;

/**
 * Created by lukasz on 12.09.17.
 */
public class Utils {
    public static void paint(List<Resizable> resizableShapes) {
        resizableShapes.forEach(resizable -> {
            resizable.setAbsoluteSize(42,42);
        });
    }
}
