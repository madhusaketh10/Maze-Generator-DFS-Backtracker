// Madhu Saketh
// Maze Generator
// Algorithm: Iterative Depth-First Search (Recursive Backtracker)
// Reference: https://en.wikipedia.org/wiki/Maze_generation_algorithm

package com.madhu;

import com.madhu.lib.Cell;
import processing.core.PApplet;

import java.util.Stack;

public class Main extends PApplet {
    private int cellSize = 60;

    private int windowWidth = 1920;
    private int windowHeight = 1080;

    private int rows = this.windowHeight / this.cellSize;
    private int cols = this.windowWidth / this.cellSize;

    private Cell[][] grid;

    private Cell currentCell;

    private Cell nextNeighbour;

    private Stack<Cell> stack = new Stack<>();

    private void init() {
        grid = new Cell[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j] = new Cell(this, i, j, this.cellSize);
            }
        }
    }

    private void show() {
        background(51);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j].show();
            }
        }
    }

    public void removeWalls(Cell a, Cell b) {
        int x = a.getI() - b.getI();

        if(x == -1) {
            a.setWalls(2, false);
            b.setWalls(0, false);
        } else if(x == 1) {
            a.setWalls(0, false);
            b.setWalls(2, false);
        }

        int y = a.getJ() - b.getJ();

        if(y == -1) {
            a.setWalls(1, false);
            b.setWalls(3, false);
        } else if(y == 1) {
            a.setWalls(3, false);
            b.setWalls(1, false);
        }
    }


    @Override
    public void settings() {
        size(windowWidth, windowHeight);
    }

    @Override
    public void setup() {
        frameRate(60);
        init();
        currentCell = grid[0][0];

    }

    @Override
    public void draw() {
        show();
        currentCell.setVisited(true);
        nextNeighbour = currentCell.checkNeighbors(this.grid, this.rows, this.cols);

        if(nextNeighbour != null) {
            stack.push(currentCell);
            nextNeighbour.setVisited(true);
            removeWalls(currentCell, nextNeighbour);
            currentCell = nextNeighbour;
        } else if(!stack.isEmpty()){
            currentCell = stack.pop();
        }
       currentCell.highlight();
    }

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }
}
