package org.snakeGame.models;

import java.util.Deque;
import java.util.LinkedList;

public class Board {
    int rows ;
    int columns;
    Cell[][] cells ;

    Deque<Cell> snakePoints ;
    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns ;
        this.cells = new Cell[rows][columns] ;
        this.snakePoints = new LinkedList<>() ;
    }

    //logic of the turn

    public void displayBoard(){
        for(int i = 0; i < rows; i++){
            for (int j = 0; j<columns; j++){
                if(cells[i][j].getState().equals(CellState.SNAKE)){
                    System.out.print('*');
                }
                if(cells[i][j].getState().equals(CellState.EMPTY)){
                    System.out.print('#');
                }
                if(cells[i][j].getState().equals(CellState.FOOD)){
                    System.out.print('o');
                }
            }
        }
    }
}
