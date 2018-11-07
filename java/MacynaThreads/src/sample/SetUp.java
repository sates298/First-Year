package sample;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * class to set up environment
 * @see Controller
 */
class SetUp {

    /**
     * array of fields
     * @see Quadrangle
     */
    private Quadrangle[][] array;
    /**
     * place where colors are placed
     */
    private GridPane grid;
    /**
     * number of columns
     */
    private int width;
    /**
     * number od rows
     */
    private int height;
    /**
     * value of delay and speed
     */
    private Long speed;
    /**
     * probability to change colors
     * @see Quadrangle
     */
    private double probability;


    /**
     * Constructor
     *
     * @param pane  is a main GridPane in program
     * @param n     is a width which is enter by user
     * @param m     is a height which is enter by user
     * @param p     is a probability which is enter by user
     * @param speed is a speed which is enter by user
     */
    SetUp(GridPane pane, int n, int m, double p, Long speed) {
        this.grid = pane;
        this.width = n;
        this.height = m;
        this.array = new Quadrangle[height][width];
        this.probability = p;
        this.speed = speed;
        //setUpGrid();
        setPanesInGrid();
    }

/*
    /**
     * set up GridPane by add columns and rows
     /
    private void setUpGrid() {

        for (int i = 0; i < height; i++) {
            RowConstraints rows = new RowConstraints();
            rows.setMinHeight(grid.getHeight() / height);
            rows.setMaxHeight(rows.getMinHeight());
            grid.getRowConstraints().add(i, rows);
        }


        for (int i = 0; i < width; i++) {
            ColumnConstraints columns = new ColumnConstraints();
            columns.setMinWidth(grid.getWidth() / width);
            columns.setMaxWidth(columns.getMinWidth());
            grid.getColumnConstraints().add(i, columns);
        }
    }*/


    /**
     * set fields in GridPane . This fields will be changes colors
     */
    private void setPanesInGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = new Quadrangle(i, j, array, probability);
                array[i][j].setSpeed(speed);
                grid.add(array[i][j], j, i);
            }
        }
    }

    /**
     * getter to GridPane
     *
     * @return ready GridPane
     */

    GridPane getGrid() {
        return this.grid;
    }

    /**
     * getter to array of variables fields
     *
     * @return ready array full of variables fields
     */
    Quadrangle[][] getArray() {
        return array;
    }
}
