package org.snakeGame.models;

import lombok.Getter;
import org.snakeGame.exceptions.InvalidDirectionException;

import java.util.Deque;
import java.util.LinkedList;

@Getter
public class Board {
    int rows ;
    int columns ;
    Cell[][] cells ;
    Direction currentSnakeDirection ;
    Deque<Cell> snakePoints ;

    public Board(int rows, int columns,Direction direction){
        this.rows = rows;
        this.columns = columns ;
        this.cells = new Cell[rows][columns] ;
        this.snakePoints = new LinkedList<>() ;
        this.currentSnakeDirection = direction ;

        for(int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                this.cells[i][j] = new Cell(CellState.EMPTY, i, j);
            }
        }

        //for testing purpose
//        this.cells[1][0].setState(CellState.SNAKE);
//        this.cells[1][1].setState(CellState.SNAKE);
//        this.cells[1][2].setState(CellState.SNAKE);
//
//        //add food
//        this.cells[3][4].setState(CellState.FOOD);
//        snakePoints.addLast(this.cells[1][0]);
//        snakePoints.addLast(this.cells[1][1]);
    }

    private Cell getNextHead(Direction direction, Cell currentHead) throws InvalidDirectionException {
        if(direction.equals(Direction.RIGHT)){
            return this.cells[currentHead.getPos_x()][(currentHead.getPos_y() + 1) % columns] ;
        }

        if(direction.equals(Direction.LEFT)){
            int nextY = currentHead.getPos_y() - 1 ;
            if (nextY < 0){
                nextY = nextY + columns ;
            }
            return this.cells[currentHead.getPos_x()][ nextY % columns] ;
        }

        if(direction.equals(Direction.DOWN)){
            return this.cells[(currentHead.getPos_x() +  1) % rows][currentHead.getPos_y()] ;
        }

        if(direction.equals(Direction.UP)){
            int nextX = currentHead.getPos_x() - 1 ;
            if (nextX < 0){
                nextX = nextX + rows ;
            }
            return this.cells[nextX % rows][currentHead.getPos_y()] ;
        }
        throw new InvalidDirectionException("Direction provided to update game state is not valid") ;
    }
    public void setSnakeHead(int xPos, int yPos){
        Cell headCell = this.cells[xPos][yPos];
        headCell.setState(CellState.SNAKE);
        snakePoints.addLast(headCell);
    }
    public void update(Direction nextDirection) throws InvalidDirectionException {
        boolean removeTail =  true;
        Cell currentHead = snakePoints.peekLast();
        assert currentHead != null;
        Cell nextHead = getNextHead(nextDirection, currentHead) ;
        if (nextHead.getState().equals(CellState.FOOD)){
            removeTail = false ;
        }
        nextHead.setState(CellState.SNAKE);
        snakePoints.addLast(nextHead);

        if (removeTail){
            Cell currentTail = this.snakePoints.pollFirst() ;
            assert currentTail != null;
            currentTail.setState(CellState.EMPTY);
        }

        this.displayBoard();
    }
    public void displayBoard(){
        for(int i = 0; i < rows; i++){
            for (int j = 0; j<columns; j++){
                if(cells[i][j].getState().equals(CellState.SNAKE)){
                    System.out.print("*  ");
                }
                if(cells[i][j].getState().equals(CellState.EMPTY)){
                    System.out.print("__  ");
                }
                if(cells[i][j].getState().equals(CellState.FOOD)){
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }
}
