package application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
public class FlowerBed {
    private Rectangle bed;
    private List<Flower> flowers = new ArrayList<>();
    public FlowerBed(double x, double y, double width, double height) {
        bed = new Rectangle(x, y, width, height);
        bed.setFill(Color.SKYBLUE);
        bed.setStroke(Color.WHITE);
    }
    public Rectangle getBed() {
        return bed;
    }
    public void addChild(Flower flower) {
        flowers.add(flower);
    }
    public void move(double dx, double dy) {
        bed.setX(bed.getX() + dx);
        bed.setY(bed.getY() + dy);
        for (Flower flower : flowers) {
            flower.move(dx, dy);
        }
    }
    public boolean contains(double x, double y) {
        return bed.getX() <= x && x <= (bed.getX() + bed.getWidth()) &&
               bed.getY() <= y && y <= (bed.getY() + bed.getHeight());
    }
}
