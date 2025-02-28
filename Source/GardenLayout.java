package application;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
public class GardenLayout extends Application {
    private Flower draggedFlower = null;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.GREEN);
        FlowerBed bed = new FlowerBed(100, 100, 150, 200);
        root.getChildren().add(bed.getBed());
        Flower flowerInBed = new Flower(300, 300, true);
        root.getChildren().add(flowerInBed.getCircle());
        Handlers(flowerInBed, bed);
        BedDrag(bed);
        primaryStage.setTitle("Garden");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void Handlers(Flower flower, FlowerBed bed) {
        flower.getCircle().setOnMousePressed(e -> {
            draggedFlower = flower;
            flower.getCircle().setUserData(new Point2D(e.getSceneX(), e.getSceneY()));
        });
        flower.getCircle().setOnMouseDragged(e -> {
            if (draggedFlower != null) {
                Point2D prev = (Point2D) flower.getCircle().getUserData();
                double dx = e.getSceneX() - prev.getX();
                double dy = e.getSceneY() - prev.getY();
                flower.move(dx, dy);
                flower.getCircle().setUserData(new Point2D(e.getSceneX(), e.getSceneY()));
            }
        });
        flower.getCircle().setOnMouseReleased(e -> {
            if (draggedFlower != null) {
                if (isInNode(draggedFlower, bed)) {
                    bed.addChild(draggedFlower);
                }
                draggedFlower = null;
            }
        });
    }
    private void BedDrag(FlowerBed bed) {
        bed.getBed().setOnMousePressed(e -> {
            bed.getBed().setUserData(new Point2D(e.getSceneX(), e.getSceneY()));
        });
        bed.getBed().setOnMouseDragged(e -> {
            Point2D prev = (Point2D) bed.getBed().getUserData();
            double dx = e.getSceneX() - prev.getX();
            double dy = e.getSceneY() - prev.getY();
            bed.move(dx, dy);
            bed.getBed().setUserData(new Point2D(e.getSceneX(), e.getSceneY()));
        });
    }
    private boolean isInNode(Flower flower, FlowerBed bed) {
        double gx = flower.getCircle().getCenterX();
        double gy = flower.getCircle().getCenterY();
        return bed.contains(gx, gy);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
