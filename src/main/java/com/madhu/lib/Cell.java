// Madhu Saketh
// Maze Generator
// Algorithm: Iterative Depth-First Search (Recursive Backtracker)
// Reference: https://en.wikipedia.org/wiki/Maze_generation_algorithm

package com.madhu.lib;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private PApplet p;

    private int i;
    private int j;

    private int cellSize;

    private boolean[] walls = {true, true, true, true};
    private boolean visited = false;

    public Cell(PApplet p, int i, int j, int cellSize) {
        this.p = p;

        this.i = i;
        this.j = j;

        this.cellSize = cellSize;

    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }

    public void setWalls(int index, boolean wall) {
        this.walls[index] = wall;
    }

    public boolean[] getWalls() {
        return this.walls;
    }

    public void setVisited(boolean visit) {
        this.visited = visit;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public Cell checkNeighbors(Cell[][] grid, int rows, int cols) {
        Cell top = null;
        Cell right = null;
        Cell bottom = null;
        Cell left = null;

        Cell val = null;

        List<Cell> neighbors = new ArrayList<>();

        if(this.i > 0) {
            top = grid[this.i - 1][this.j];
        }

        if (this.i < rows - 1) {
            bottom = grid[this.i + 1][this.j];
        }

        if (this.j > 0) {
            left = grid[this.i][this.j - 1];
        }

        if(this.j < cols - 1) {
            right = grid[this.i][this.j + 1];
        }


        if(top != null && !top.visited) {
            neighbors.add(top);
        }

        if(bottom != null && !bottom.visited) {
            neighbors.add(bottom);
        }

        if(right != null && !right.visited) {
            neighbors.add(right);
        }

        if(left != null && !left.visited) {
            neighbors.add(left);
        }

        if(!neighbors.isEmpty()) {
            int r = PApplet.floor(p.random(0, neighbors.size()));
            val = neighbors.get(r);
        }

        return val;
    }

    public void highlight() {
        int x = j * this.cellSize;
        int y = i * this.cellSize;
        p.noStroke();
        p.fill(0, 255, 0, 150);
        p.rect(x, y, this.cellSize, this.cellSize);
    }

    public void show() {
        int x = j * this.cellSize;
        int y = i * this.cellSize;

        p.stroke(255);

        if(this.walls[0]) {
            p.line(x, y, x + this.cellSize, y);
        }

        if(this.walls[1]) {
            p.line(x + this.cellSize, y, x + this.cellSize, y + this.cellSize);
        }

        if(this.walls[2]) {
            p.line(x + this.cellSize, y + this.cellSize, x, y + this.cellSize);

        }

        if(this.walls[3]) {
            p.line(x, y + this.cellSize, x, y);
        }

        p.noStroke();

        if (this.visited) {
            p.noStroke();
            p.fill(127, 0, 255, 50);
            p.rect(x, y, this.cellSize, this.cellSize);
        }
    }
}
