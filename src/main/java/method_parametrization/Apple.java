package method_parametrization;

/**
 * Created by lukasz on 17.06.17.
 */
public class Apple implements Fruit {
    private String color;
    private Integer id;
    private int weight;

    public Apple(Integer id, int weight, String color) {
        this.id = id;
        this.weight = weight;
        this.color = color;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + getId());
        sb.append(" color: " + getColor());
        sb.append(" weight: " + getIntWeight());
        return sb.toString();
    }

    @Override
    public int getIntWeight() {
        return weight;
    }

    @Override
    public String getStringWeight(){
        return String.valueOf(weight);
    }


}
