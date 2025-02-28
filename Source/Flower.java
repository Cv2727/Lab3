package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower {
    private Circle circle;

    public Flower(double x, double y, boolean visible) {
        circle = new Circle(x, y, 10, Color.RED);
        circle.setStroke(Color.BLACK); 
        circle.setVisible(visible);
    }

    public Circle getCircle() {
        return circle;
    }

    public void move(double dx, double dy) {
        circle.setCenterX(circle.getCenterX() + dx);
        circle.setCenterY(circle.getCenterY() + dy);
    }
}
