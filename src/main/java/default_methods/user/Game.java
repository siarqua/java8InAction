package default_methods.user;

import default_methods.framework.Resizable;
import default_methods.user.shapes.Elipse;
import default_methods.user.shapes.Monster;
import default_methods.user.shapes.Rectangle;
import default_methods.user.shapes.Squere;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lukasz on 12.09.17.
 */
public class Game {
    public static void main(String[] args) {
        List<Resizable> resizableShapes = Arrays.asList(new Squere(), new Rectangle(), new Elipse());
        Utils.paint(resizableShapes);
        Monster monster = new Monster();
        monster.rotateBy(180);
        monster.moveVertically(10);
    }
}
