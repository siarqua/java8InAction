package refactoring_testing_debagging;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lukasz on 08.09.17.
 */
public class Point {
    private final int x;
    private final int y;

    // Lambda comparator
    public final static Comparator<Point> compareByXAndThenY =
            Comparator.comparing(Point::getX).thenComparing(Point::getY);

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x){
        return new Point(this.x + x, this.y);
    }

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
        return points.stream()
                .map(p -> new Point(p.getX()+x, p.getY()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "x: " + getX() + " y: " + getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (getX() != point.getX()) return false;
        return getY() == point.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }
}
