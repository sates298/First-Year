package sample;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * field od GridPane which could change color
 * @see SetUp
 */
public class Quadrangle extends Pane implements Runnable {

    /**
     * array where is this Quadrangle
     */
    private Quadrangle[][] array;
    /**
     * row index where this Quadrangle is placed
     */
    private int i;
    /**
     * column index where this Quadrangle is placed
     */
    private int j;
    /**
     * value of probability to change color this Quadrangle
     */
    private double p;
    /**
     * value of speed to generate random value of change color delay
     */
    private Long speed;

    /**
     * Constructor
     *
     * @param i           index of column where is this quadrangle
     * @param j           index of row where is this quadrangle
     * @param ar          array where is this quadrangle
     * @param probability probability of change color
     */
    Quadrangle(int i, int j, Quadrangle[][] ar, double probability) {
        this.i = i;
        this.j = j;
        this.array = ar;
        this.p = probability;
        this.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        setBackground(new Background(new BackgroundFill(randomColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * method to generate random colors
     *
     * @return random color
     */
    private Color randomColor() {
        int green = (new Random().nextInt()) % 256;
        int red = (new Random().nextInt()) % 256;
        int blue = (new Random().nextInt()) % 256;
        if (red < 0) {
            red = (-1) * red;
        }
        if (green < 0) {
            green = (-1) * green;
        }
        if (blue < 0) {
            blue = (-1) * blue;
        }
        return Color.rgb(red, green, blue);
    }

    /**
     * change Quadrangle color on random color
     */
    private void changeBackgroundRandomColor() {
        this.setBackground(new Background(new BackgroundFill(randomColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * change Quadrangle color as average colors value neighbors
     */
    private void changeBackgroundAsAverage() {
        Color color;
        double avRed = 0.25 * (getOneFromRGB(checkBorders(i, j - 1, array), "red") +
                getOneFromRGB(checkBorders(i, j + 1, array), "red") +
                getOneFromRGB(checkBorders(i - 1, j, array), "red") +
                getOneFromRGB(checkBorders(i + 1, j, array), "red"));
        double avGreen = 0.25 * (getOneFromRGB(checkBorders(i, j - 1, array), "green") +
                getOneFromRGB(checkBorders(i, j + 1, array), "green") +
                getOneFromRGB(checkBorders(i - 1, j, array), "green") +
                getOneFromRGB(checkBorders(i + 1, j, array), "green"));
        double avBlue = 0.25 * (getOneFromRGB(checkBorders(i, j - 1, array), "blue") +
                getOneFromRGB(checkBorders(i, j + 1, array), "blue") +
                getOneFromRGB(checkBorders(i - 1, j, array), "blue") +
                getOneFromRGB(checkBorders(i + 1, j, array), "blue"));

        color = Color.color(avRed, avGreen, avBlue);
        this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * @param i     index of column where is wanted quadrangle, may be less then 0 or more then number of columns
     * @param j     index of rows where is wanted quadrangle, may be less then 0 or more then number of rows
     * @param array array where is wanted quadrangle
     * @return quadrangle with right index's
     */
    private Quadrangle checkBorders(int i, int j, Quadrangle[][] array) {
        int index = (i + array.length) % array.length;
        return array[index][(j + array[index].length) % array[index].length];
    }

    /**
     * @param q     Quadrangle which give own one color value(red,green or blue) to count average
     * @param color which color (red,green or blue) we wanted to get
     * @return double value of wanted color
     */
    private double getOneFromRGB(Quadrangle q, String color) {
        switch (color) {
            case "red":
                return ((Color) q.getBackground().getFills().get(0).getFill()).getRed();
            case "green":
                return ((Color) q.getBackground().getFills().get(0).getFill()).getGreen();
            case "blue":
                return ((Color) q.getBackground().getFills().get(0).getFill()).getBlue();
            default:
                return 0;
        }
    }

    /**
     * change field's color with some probability
     */
    private void setFieldColor() {
        double probability = new Random().nextDouble();

        if (probability <= p) {
            this.changeBackgroundRandomColor();
        } else if (probability <= 1 - p) {
            this.changeBackgroundAsAverage();
        }
    }

    /**
     * setter to speed
     * @param speed is a value which is enter by user to count a delay time
     */
    void setSpeed(Long speed) {
        Long delay = new Random().nextLong() % speed;
        if (delay < 0) {
            delay = delay * (-1);
        }
        delay = delay + speed / 2;
        this.speed = delay;
    }

    /**
     * run method
     */
    @Override
    public void run() {
        try {
            while (true) {
                Platform.runLater(this::setFieldColor);
                Thread.sleep(speed);
            }
        } catch (Exception ignored) {
        }
    }
}
