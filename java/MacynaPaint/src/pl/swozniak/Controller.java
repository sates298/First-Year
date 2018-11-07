package pl.swozniak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.plugin.dom.css.Rect;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Stanisław Woźniak
 * Controller to file fxml
 * @version 1.0
 */
public class Controller {

    @FXML
    private ColorPicker colors;
    @FXML
    private Text valueX;
    @FXML
    private Text valueY;
    @FXML
    private ToggleGroup toggleButtons;
    @FXML
    private Pane pane;

    private Color color = Color.WHITE;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    private List<Double> points = new ArrayList<>();
    private String fileName = "/home/stachu/Desktop/macynaPaint.txt";

    /**
     * Method use to moving shape on pane
     *
     * @param s is the shape which is moved
     */
    private void shapeMovement(Shape s) {

        s.setOnMousePressed(event -> {
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            orgTranslateX = s.getTranslateX();
            orgTranslateY = s.getTranslateY();

        });

        s.setOnMouseDragged(event -> {

            if (event.getButton().equals(MouseButton.PRIMARY)) {
                /*double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                s.setTranslateX(orgTranslateX + offsetX);
                s.setTranslateY(orgTranslateY + offsetY);
                valueX.setText("X = " + event.getSceneX());
                valueY.setText("Y = " + event.getSceneY());*/

                if (s.getClass() == Rectangle.class) {
                    ((Rectangle) s).setX(event.getX());
                    ((Rectangle) s).setY(event.getY());
                } else if (s.getClass() == Circle.class) {
                    ((Circle) s).setCenterY(event.getY());
                    ((Circle) s).setCenterX(event.getX());
                } else if (s.getClass() == Polygon.class) {

                    double offsetX = event.getSceneX() - orgSceneX;
                    double offsetY = event.getSceneY() - orgSceneY;
                    s.setTranslateX(orgTranslateX + offsetX);
                    s.setTranslateY(orgTranslateY + offsetY);

                }

                valueX.setText("X = " + event.getSceneX());
                valueY.setText("Y = " + event.getSceneY());

            }


        });


    }

    /**
     * gets Shape from pane
     * @param s is scrolled shape
     */

    private void scrollShape(Shape s){
        if(s.getClass() == Circle.class){
            s.setOnScroll(e -> {
                double zoomValue = 1.05;
                if (e.getDeltaY() < 0) {
                    zoomValue = 0.95;
                }
                ((Circle) (e.getSource()))
                        .setRadius(((Circle) e.getSource()).getRadius() * zoomValue);
            });
        } else if(s.getClass() == Rectangle.class){
            s.setOnScroll(e -> {
                double zoomValue = 1.05;
                if (e.getDeltaY() < 0) {
                    zoomValue = 0.95;
                }
                if (e.isControlDown()) {
                    ((Rectangle) e.getSource())
                            .setWidth(((Rectangle) e.getSource()).getWidth() * zoomValue);
                } else if (e.isShiftDown()) {
                    ((Rectangle) e.getSource())
                            .setHeight(((Rectangle) e.getSource()).getHeight() * zoomValue);
                } else {
                    ((Rectangle) e.getSource())
                            .setWidth(((Rectangle) e.getSource()).getWidth() * zoomValue);
                    ((Rectangle) e.getSource())
                            .setHeight(((Rectangle) e.getSource()).getHeight() * zoomValue);
                }
            });
        }else if (s.getClass() == Polygon.class){
            s.setOnScroll(e -> {
                double zoomValue = 1.05;
                if(e.getDeltaY() <0){
                    zoomValue = 0.95;
                }
                Polygon p = (Polygon) s;
                double[] points = p.getPoints().stream().mapToDouble(Number::doubleValue).toArray();
                Scale scale = new Scale(zoomValue, zoomValue, e.getX(), e.getY());
                scale.transform2DPoints(points ,0, points, 0, points.length/2);
                for(int i =0; i<points.length; i++){
                    p.getPoints().set(i, points[i]);
                }





               /* double zoomValue = 1.05;
                if (e.getDeltaY() < 0) {
                    zoomValue = 0.95;
                }
                ((Polygon) e.getSource())
                        .setScaleX(((Polygon) e.getSource()).getScaleX() * zoomValue);
                ((Polygon) e.getSource())
                        .setScaleY(((Polygon) e.getSource()).getScaleY() * zoomValue);*/






            });
        }
    }


    /**
     * change shape's color
     *
     * @param s is the shape which color is change
     */
    private void changeColor(Shape s) {
        s.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                ((Shape) e.getSource()).setFill(color);
            }
        });
    }

    /**
     * gets MouseEvent
     *
     * @param event is using to create and move shapes
     */
    @FXML
    private void drawShape(MouseEvent event) {

        if (toggleButtons.getSelectedToggle() == null) {
            return;
        }

        switch (((ToggleButton) toggleButtons.getSelectedToggle()).getId()) {

            case "rectangle":
                Rectangle rectangle = new Rectangle();
                rectangle.setLayoutY(event.getY());
                rectangle.setLayoutX(event.getX());
                rectangle.setWidth(100);
                rectangle.setHeight(50);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);

                changeColor(rectangle);
                shapeMovement(rectangle);
                scrollShape(rectangle);

                pane.getChildren().add(rectangle);
                break;

            case "circle":
                Circle circle = new Circle();
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                circle.setRadius(50);
                shapeMovement(circle);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                changeColor(circle);
                scrollShape(circle);
                pane.getChildren().add(circle);
                break;

            case "polygon":
                Line point = new Line();

                point.setLayoutX(event.getX());
                point.setLayoutY(event.getY());
                points.add(point.getLayoutX());
                points.add(point.getLayoutY());
                pane.getChildren().addAll(point);
                Polygon polygon = new Polygon();
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    polygon.getPoints().addAll(points);
                    polygon.setFill(Color.WHITE);
                    polygon.setStroke(Color.BLACK);
                    pane.getChildren().add(polygon);
                    points = new ArrayList<>();
                }
                scrollShape(polygon);
                changeColor(polygon);
                shapeMovement(polygon);
                break;
        }

        Stage st = (Stage) pane.getScene().getWindow();
        st.show();
    }


    /**
     * clear pane
     */
    @FXML
    private void clear() {
        pane.getChildren().clear();
    }

    /**
     * set color by colorpicker
     */
    @FXML
    private void setColor() {
        color = colors.getValue();
    }

    /**
     * save picture to .txt file
     */
    @FXML
    private void save() {

        try {
            PrintWriter print = new PrintWriter(new File(fileName));
            for (Node n : pane.getChildren()) {
                if (n.getClass() == Rectangle.class) {
                    Rectangle r = (Rectangle) n;
                    print.println("r " + r.getX() + " "
                            + r.getY() + " "
                            + r.getWidth() + " "
                            + r.getHeight() + " "
                            + r.getFill().toString());
                } else if (n.getClass() == Circle.class) {
                    Circle c = (Circle) n;
                    print.println("c " + c.getCenterX() + " "
                            + c.getCenterY() + " "
                            + c.getRadius() + " "
                            + c.getFill().toString());
                } else if (n.getClass() == Polygon.class) {
                    Polygon p = (Polygon) n;
                    print.println("p " + p.getPoints().toString() + " " + p.getFill().toString());
                }
            }

            print.close();

        } catch (Exception ignored) {

        }


    }

    /**
     * load exist picture
     */
    @FXML
    private void load() {

        try {

            pane.getChildren().clear();
            FileReader fr = new FileReader(new File(fileName));
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                List<String> list = asList(line.split(" |\\[|, |\\]"));
                switch (list.get(0)) {
                    case "r":
                        Rectangle r = new Rectangle(Double.parseDouble(list.get(1))
                                , Double.parseDouble(list.get(2))
                                , Double.parseDouble(list.get(3))
                                , Double.parseDouble(list.get(4)));
                        r.setFill(Paint.valueOf(list.get(5)));
                        r.setStroke(Color.BLACK);

                        pane.getChildren().add(r);
                        shapeMovement(r);
                        changeColor(r);
                        scrollShape(r);
                        break;
                    case "c":
                        Circle c = new Circle(Double.parseDouble(list.get(1))
                                , Double.parseDouble(list.get(2))
                                , Double.parseDouble(list.get(3)));
                        c.setFill(Paint.valueOf(list.get(4)));
                        c.setStroke(Color.BLACK);

                        pane.getChildren().addAll(c);
                        shapeMovement(c);
                        changeColor(c);
                        scrollShape(c);
                        break;
                    case "p":
                        for(String s: list){
                            System.out.println(s);
                        }
                        Polygon p = new Polygon();

                        List<Double> points = new ArrayList<>();
                        for (int i = 2; i < list.size() - 2; i++) {
                            points.add(Double.parseDouble(list.get(i)));
                        }

                        p.getPoints().addAll(points);

                        p.setFill(Paint.valueOf(list.get(2)));
                        p.setStroke(Color.BLACK);

                        pane.getChildren().addAll(p);
                        shapeMovement(p);
                        changeColor(p);
                        scrollShape(p);
                        break;
                }
            }


            Stage st = (Stage) pane.getScene().getWindow();
            st.show();

        } catch (Exception ignored) {
        }

    }


    /**
     * open information window
     * Window has information about program's author, program's destiny and program's name
     */
    @FXML
    private void openInfoWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText(null);
        alert.setContentText("Program name: Macyna Paint \nDestiny: good grade \nAuthor: Stanisław Woźniak");
        alert.show();
    }

}
